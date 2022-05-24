package com.cn.exams.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cn.exams.ui.bank.BankFragment
import com.cn.exams.ui.homeinside.HomeInsideFragment
import com.cn.exams.ui.other.OtherFragment

class HomeVPAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeInsideFragment()
            1 -> BankFragment()
            else -> OtherFragment()
        }
    }
}