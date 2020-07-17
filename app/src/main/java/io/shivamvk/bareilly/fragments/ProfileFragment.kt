package io.shivamvk.bareilly.fragments

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import io.shivamvk.bareilly.R
import io.shivamvk.bareilly.adapters.ProfileViewPagerAdapter
import io.shivamvk.bareilly.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(), OnOffsetChangedListener {

    lateinit var binding: FragmentProfileBinding
    var isShow = true
    var scrollRange = -1

    companion object {
        fun newInstance(): Fragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.profileViewPager.adapter = ProfileViewPagerAdapter(childFragmentManager)
        binding.profileTabs.setupWithViewPager(binding.profileViewPager)
        binding.profileAppBar.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (scrollRange == -1){
            scrollRange = appBarLayout!!.totalScrollRange
        }
        if (scrollRange + verticalOffset == 0) {
            binding.profileCollapsingSection.title = "Shivam Bhasin"
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                binding.profileCollapsingSection.setCollapsedTitleTextColor(
                    resources.getColor(
                        R.color.colorWhite
                    )
                )
            } else {
                binding.profileCollapsingSection.setCollapsedTitleTextColor(
                    resources.getColor(
                        R.color.colorBlack
                    )
                )
            }
            binding.expandedToolbarContent.visibility = View.INVISIBLE
            isShow = true
        } else {
            binding.profileCollapsingSection.title = " "
            binding.expandedToolbarContent.visibility = View.VISIBLE
            isShow = false
        }
    }
}