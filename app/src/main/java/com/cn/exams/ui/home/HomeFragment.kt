package com.cn.exams.ui.home

import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.cn.exams.R
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentHomeBinding
import com.cn.exams.ui.home.adapter.HomeVPAdapter

class HomeFragment
    : BaseFragment<FragmentHomeBinding, HomeContract.Presenter>(), HomeContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    override val setupPresenter: () -> HomeContract.Presenter
        get() = { HomePresenter(this) }
    override val setupViewModel: (HomeContract.Presenter) -> Unit
        get() = binding::setPresenter

    override fun initUI() {
        configBNV()
    }

    private fun configBNV() {
        binding.vp.adapter = HomeVPAdapter(this)
        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bnv.selectedItemId = R.id.menu_home
                    1 -> binding.bnv.selectedItemId = R.id.menu_bank
                    else -> binding.bnv.selectedItemId = R.id.menu_more
                }
            }
        })

        binding.bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> binding.vp.currentItem = 0
                R.id.menu_bank -> binding.vp.currentItem = 1
                else -> binding.vp.currentItem = 2
            }
            true
        }
    }

    fun toLogin() {
        navigation.navigate(
            R.id.action_fragment_home_to_fragment_login
        )
    }
}