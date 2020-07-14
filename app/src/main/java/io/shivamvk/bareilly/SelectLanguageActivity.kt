package io.shivamvk.bareilly

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import io.shivamvk.bareilly.databinding.ActivitySelectLanguageBinding
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper
import io.shivamvk.bareilly.sharedPrefs.SharedPrefKeys
import java.util.*
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper.set
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper.get

class SelectLanguageActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivitySelectLanguageBinding
    lateinit var selectedLocale: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
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
                    val prefs = PreferencesHelper.appPrefs(this)
                    if (prefs[SharedPrefKeys.APP_LOCALE.toString(), ""] == ""){
                        if (selectedLocale == "en") {
                            prefs[SharedPrefKeys.APP_LOCALE.toString()] =
                                resources.getString(R.string.english)
                        } else {
                            prefs[SharedPrefKeys.APP_LOCALE.toString()] =
                                resources.getString(R.string.hindi)
                        }
                        finish()
                        startActivity(Intent(
                            this, SelectThemeActivity::class.java
                        ))
                    } else {
                        if (selectedLocale == "en") {
                            prefs[SharedPrefKeys.APP_LOCALE.toString()] =
                                resources.getString(R.string.english)
                        } else {
                            prefs[SharedPrefKeys.APP_LOCALE.toString()] =
                                resources.getString(R.string.hindi)
                        }
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                }
            }
        }
    }
}