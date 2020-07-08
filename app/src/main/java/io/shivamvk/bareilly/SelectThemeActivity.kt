package io.shivamvk.bareilly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import io.shivamvk.bareilly.databinding.ActivitySelectThemeBinding

class SelectThemeActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivitySelectThemeBinding
    var darkThemeSelected: Boolean = false
    var optionSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.cvLightThemeOption.setOnClickListener(this)
        binding.cvDarkThemeOption.setOnClickListener(this)
        binding.btThemeSelectionContinue.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cv_light_theme_option -> {
                darkThemeSelected = false
                optionSelected = true
                binding.tvLightThemeOption.setTextColor(resources.getColor(R.color.colorPurpule))
                binding.tvDarkThemeOption.setTextColor(resources.getColor(R.color.colorWhite))
                binding.tvLightThemeOption.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins_bold)
                binding.tvDarkThemeOption.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins)
            }
            R.id.cv_dark_theme_option -> {
                darkThemeSelected = true
                optionSelected = true
                binding.tvLightThemeOption.setTextColor(resources.getColor(R.color.colorBlack))
                binding.tvDarkThemeOption.setTextColor(resources.getColor(R.color.colorPink))
                binding.tvLightThemeOption.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins)
                binding.tvDarkThemeOption.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins_bold)
            }
            R.id.bt_theme_selection_continue -> {
                if (optionSelected){
                    if (darkThemeSelected){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                } else {
                    Toast.makeText(applicationContext, resources.getString(R.string.pick_a_theme), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}