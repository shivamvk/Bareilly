package io.shivamvk.bareilly.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.shivamvk.bareilly.MainActivity
import io.shivamvk.bareilly.R
import io.shivamvk.bareilly.adapters.TopCategoryPostsAdapter
import io.shivamvk.bareilly.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment: Fragment(), View.OnClickListener {

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

    private fun setUpGreetingText() {
        var date = Date()
        var hour = SimpleDateFormat("hh").format(date).toInt()
        var text = ""
        if (SimpleDateFormat("a").format(date) == "pm" && hour != 12) { hour+=12 }
        if (SimpleDateFormat("a").format(date) == "am" && hour == 12){ hour = 0 }
        text = when (hour) {
            in 6..11 -> { resources.getString(R.string.good_morning) }
            in 12..17 -> { resources.getString(R.string.good_afternoon) }
            in 18..21 -> { resources.getString(R.string.good_evening) }
            else -> { resources.getString(R.string.good_night) }
        }
        binding.greetingText.text = text
    }

    private fun getTopNews(){
        var topCategoryPostsAdapter = TopCategoryPostsAdapter(context)
        binding.newsListView.adapter = topCategoryPostsAdapter
    }

    private fun init() {
        binding.homeStaySafeAnim.setAnimation(R.raw.stay_home_stay_safe)
        binding.homeStaySafeAnim.playAnimation()
        binding.newsListView.layoutManager = LinearLayoutManager(context)
        binding.settingsIconView.setOnClickListener(this)
        setUpGreetingText()
        getTopNews()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.settings_icon_view -> {
                (activity as MainActivity?)?.loadSettingsFragment()
            }
        }
    }
}