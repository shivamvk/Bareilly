package io.shivamvk.bareilly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import io.shivamvk.bareilly.adapters.OnBoardingAdapter
import io.shivamvk.bareilly.databinding.ActivityOnBoardingBinding
import java.util.*

class OnBoardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnBoardingBinding
    private var timer: Timer? = null
    private var currentPosition = 0
    private var onBoardingAdapter: OnBoardingAdapter? = null
    private var lottieAnimationView: LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setOnBoardAdapter()
        supportActionBar?.hide()
        binding.btnContinue.setOnClickListener {
            startActivity(
                Intent(this, SelectLanguageActivity::class.java)
            )
        }
    }

    private fun setOnBoardAdapter() {
        onBoardingAdapter = OnBoardingAdapter(this)
        binding.viewPager!!.adapter = onBoardingAdapter
        binding.viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                callAnimation(position)
                when (position) {
                    0 -> {
                        binding.sliderDots!!.setImageResource(R.drawable.onboarding_icon1)
                    }

                    1 -> {
                        binding.sliderDots!!.setImageResource(R.drawable.onboarding_icon2)
                    }
                    2 -> {
                        binding.sliderDots!!.setImageResource(R.drawable.onboarding_icon3)
                    }
                    3 -> {
                        binding.sliderDots!!.setImageResource(R.drawable.onboarding_icon4)
                    }
                }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
        createSlideShow()
    }

    private fun callAnimation(position: Int) {
        if (lottieAnimationView != null) {
            lottieAnimationView?.cancelAnimation()
            lottieAnimationView = null
        }
        lottieAnimationView = onBoardingAdapter?.sparseArray?.get(position)
        lottieAnimationView?.repeatCount = 0
        lottieAnimationView?.playAnimation()
    }

    private fun createSlideShow() {
        var handler= Handler()
        var runnable = Runnable {
            if(currentPosition==4)
                currentPosition=0
            binding.viewPager?.setCurrentItem(currentPosition++,true)
        }
        timer= Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 250,6000)
    }
}