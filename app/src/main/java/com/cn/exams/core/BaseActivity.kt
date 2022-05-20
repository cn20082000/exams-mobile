package com.cn.exams.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BD: ViewDataBinding, PS: BaseContract.Presenter>
    : AppCompatActivity() {

    protected val binding: BD by lazy { setupBinding(layoutInflater) }
    protected val presenter: PS by lazy { setupPresenter() }

    protected abstract val setupBinding: (LayoutInflater) -> BD
    protected abstract val setupPresenter: () -> PS
    protected abstract val setupViewModel: (PS) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        setupViewModel(presenter)
        setContentView(binding.root)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    open fun initUI() {}

    open fun updateUI() {}
}