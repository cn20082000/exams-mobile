package com.cn.exams.ui.main

import android.view.LayoutInflater
import com.cn.exams.core.BaseActivity
import com.cn.exams.databinding.ActivityMainBinding

class MainActivity
    : BaseActivity<ActivityMainBinding, MainContract.Presenter>(), MainContract.View {

    override val setupBinding: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    override val setupPresenter: () -> MainContract.Presenter
        get() = { MainPresenter(this) }
    override val setupViewModel: (MainContract.Presenter) -> Unit
        get() = binding::setPresenter
}