package io.shivamvk.bareilly.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import io.shivamvk.bareilly.fragments.HomeFragment

class ProfileViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return HomeFragment.newInstance()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Posts"
            1 -> return "Queries"
            2 -> return "Answers"
        }
        return "Error"
    }

    override fun getCount(): Int = 3
}

