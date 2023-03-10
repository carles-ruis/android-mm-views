package com.carles.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.carles.common.Navigate
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    @Inject
    lateinit var navigate: Navigate

    private var _binding: T? = null
    protected val binding get() = _binding!!

    protected val navController by lazy {
        Navigation.findNavController(requireView())
    }

    protected abstract val progress: View?

    abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = setBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun showProgress() {
        progress?.isVisible = true
    }

    protected fun hideProgress() {
        progress?.isVisible = false
    }
}
