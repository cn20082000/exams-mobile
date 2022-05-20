package com.cn.exams.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<BD: ViewDataBinding, PS: BaseContract.Presenter>
    : Fragment() {

    protected val binding: BD by lazy { setupBinding(layoutInflater) }
    protected val presenter: PS by lazy { setupPresenter() }
    protected val navigation by lazy { findNavController() }

    protected abstract val setupBinding: (LayoutInflater) -> BD
    protected abstract val setupPresenter: () -> PS
    protected abstract val setupViewModel: (PS) -> Unit

    private var isInit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isInit) {
            isInit = true
            binding.lifecycleOwner = viewLifecycleOwner
            setupViewModel(presenter)
            initUI()
        }

        updateUI()
    }

    open fun initUI() {}

    open fun updateUI() {}
}