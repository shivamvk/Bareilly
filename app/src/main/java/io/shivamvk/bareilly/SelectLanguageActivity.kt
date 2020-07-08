package io.shivamvk.bareilly

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.System.getConfiguration
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import io.shivamvk.bareilly.R
import io.shivamvk.bareilly.databinding.ActivitySelectLanguageBinding
import io.shivamvk.bareilly.sharedPrefs.SharedPrefs
import java.util.*

class SelectLanguageActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivitySelectLanguageBinding
    lateinit var selectedLocale: String
    lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        prefs = SharedPrefs.defaultPrefs(this)
        selectedLocale = ""
        binding.tvLanguageOptionEn.setOnClickListener(this)
        binding.tvLanguageOptionHi.setOnClickListener(this)
        binding.btLanguageSelectionContinue.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_language_option_en -> {
                selectedLocale = "en"
                binding.tvLanguageOptionEn.setTextColor(resources.getColor(R.color.colorPurpule))
                binding.tvLanguageOptionHi.setTextColor(resources.getColor(R.color.colorBlack))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    binding.tvLanguageOptionEn.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins_bold)
                    binding.tvLanguageOptionHi.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins)
                }
            }
            R.id.tv_language_option_hi -> {
                selectedLocale = "hi"
                binding.tvLanguageOptionHi.setTextColor(resources.getColor(R.color.colorPurpule))
                binding.tvLanguageOptionEn.setTextColor(resources.getColor(R.color.colorBlack))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    binding.tvLanguageOptionHi.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins_bold)
                    binding.tvLanguageOptionEn.typeface = ResourcesCompat.getFont(applicationContext, R.font.poppins)
                }
            }
            R.id.bt_language_selection_continue -> {
                if (selectedLocale.isEmpty()){
                    Toast.makeText(
                        this,
                        resources.getString(R.string.please_select_your_language_en) + " / " + resources.getString(R.string.please_select_your_language_hi),
                        Toast.LENGTH_SHORT).show();
                } else {
                    val dm: DisplayMetrics = resources.displayMetrics
                    val conf: Configuration = resources.configuration
                    conf.locale = Locale(selectedLocale)
                    resources.updateConfiguration(conf, dm)
                    startActivity(Intent(this, SelectThemeActivity::class.java))
                }
            }
        }
    }
}