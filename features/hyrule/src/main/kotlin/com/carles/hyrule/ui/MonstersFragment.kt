package com.carles.hyrule.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import com.carles.common.ui.BaseFragment
import com.carles.common.ui.ERROR
import com.carles.common.ui.LOADING
import com.carles.common.ui.SUCCESS
import com.carles.common.ui.dp
import com.carles.common.ui.safeNavigate
import com.carles.hyrule.HyruleGraphDirections
import com.carles.hyrule.R
import com.carles.hyrule.databinding.FragmentMonstersBinding
import com.carles.hyrule.ui.ErrorDialogFragment.Companion.REQUEST_CODE_RETRY
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonstersFragment : BaseFragment<FragmentMonstersBinding>() {

    private val viewModel: MonstersViewModel by viewModels()

    override val progress: View
        get() = binding.monstersProgress.progress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResultListener()
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMonstersBinding {
        return FragmentMonstersBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        binding.monstersRecycler.addItemDecoration(
            MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL).apply {
                dividerInsetStart = 16.dp
                dividerInsetEnd = 16.dp
            }
        )
        binding.monstersRecycler.adapter = MonstersAdapter { monster ->
            navController.safeNavigate { HyruleGraphDirections.toMonsterDetail(monster.id) }
        }
        observeMonsters()
    }

    private fun setResultListener() {
        setFragmentResultListener(REQUEST_CODE_RETRY) { _, _ ->
            viewModel.retry()
        }
    }

    private fun setupMenu() {
        (activity as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.monsters_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return menuItem.onNavDestinationSelected(findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun observeMonsters() {
        viewModel.monsters.observe(viewLifecycleOwner) { result ->
            when (result.state) {
                SUCCESS -> {
                    hideProgress()
                    (binding.monstersRecycler.adapter as MonstersAdapter).submitList(result.data)
                }
                ERROR -> {
                    hideProgress()
                    navController.safeNavigate {
                        HyruleGraphDirections.toErrorDialog(extraMessage = result.message, extraRetry = true)
                    }
                }
                LOADING -> showProgress()
            }
        }
    }
}