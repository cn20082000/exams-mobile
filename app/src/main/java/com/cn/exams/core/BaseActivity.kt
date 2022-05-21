package com.cn.exams.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.cn.exams.util.isNotNull

abstract class BaseActivity<BD: ViewDataBinding, PS: BaseContract.Presenter>
    : AppCompatActivity() {

    protected val binding: BD by lazy { setupBinding(layoutInflater) }
    protected val presenter: PS by lazy { setupPresenter() }
    private var backPressedCallback: (() -> Unit)? = null

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

    override fun onBackPressed() {
        backPressedCallback?.let { it() }
    }

    fun realBackPressed() {
        super.onBackPressed()
    }

    fun registerBackPressedCallback(callback: (() -> Unit)) {
        backPressedCallback = callback
    }
}