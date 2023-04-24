package com.carles.hyrule.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.carles.common.ui.BaseFragment
import com.carles.common.ui.ERROR
import com.carles.common.ui.LOADING
import com.carles.common.ui.SUCCESS
import com.carles.common.ui.safeNavigate
import com.carles.hyrule.HyruleGraphDirections
import com.carles.hyrule.MonsterDetail
import com.carles.hyrule.databinding.FragmentMonsterDetailBinding
import com.carles.hyrule.ui.ErrorDialogFragment.Companion.REQUEST_CODE_RETRY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonsterDetailFragment : BaseFragment<FragmentMonsterDetailBinding>() {

    private val viewModel: MonsterDetailViewModel by viewModels()

    override val progress: View
        get() = binding.monsterProgress.progress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResultListener()
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMonsterDetailBinding {
        return FragmentMonsterDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMonsterDetail()
    }

    private fun setResultListener() {
        setFragmentResultListener(REQUEST_CODE_RETRY) { _, _ ->
            viewModel.retry()
        }
    }

    private fun observeMonsterDetail() {
        viewModel.monsterDetail.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                SUCCESS -> {
                    hideProgress()
                    result.data?.let { monster -> showMonsterDetail(monster) }
                }
                ERROR -> {
                    hideProgress()
                    navController.safeNavigate {
                        HyruleGraphDirections.toErrorDialog(extraMessage = result.message, extraRetry = false)
                    }
                }
                LOADING -> showProgress()
            }
        }
    }

    private fun showMonsterDetail(monster: MonsterDetail) {
        (activity as AppCompatActivity).supportActionBar?.title = monster.name
        binding.monsterContent.visibility = VISIBLE
        binding.monsterLocations.text = monster.commonLocations
        binding.monsterDescription.text = monster.description
        Glide.with(this)
            .load(monster.image)
            .centerInside()
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.monsterImageUrl.visibility = VISIBLE
                    binding.monsterImageUrl.text = monster.image
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(binding.monsterImage)
    }

    companion object {
        const val EXTRA_ID = "extraId"
        fun getBundle(id: Int) = bundleOf(EXTRA_ID to id)
    }
}