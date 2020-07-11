package io.shivamvk.bareilly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.shivamvk.bareilly.R
import io.shivamvk.bareilly.adapters.TopCategoryPostsAdapter
import io.shivamvk.bareilly.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding

    companion object {
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun getTopNews(){
        var topCategoryPostsAdapter = TopCategoryPostsAdapter(context)
        binding.newsListView.adapter = topCategoryPostsAdapter
    }

    private fun init() {
        binding.homeStaySafeAnim.setAnimation(R.raw.stay_home_stay_safe)
        binding.homeStaySafeAnim.playAnimation()
        binding.newsListView.layoutManager = LinearLayoutManager(context)
        getTopNews()
    }
}