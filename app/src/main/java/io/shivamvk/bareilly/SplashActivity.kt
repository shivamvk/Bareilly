package io.shivamvk.bareilly

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatDelegate
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper.get
import io.shivamvk.bareilly.sharedPrefs.SharedPrefKeys
import java.util.*

class SplashActivity : AppCompatActivity() {

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val dm: DisplayMetrics = resources.displayMetrics
        val conf: Configuration = resources.configuration
        conf.locale = Locale("hi")
        prefs = PreferencesHelper.appPrefs(baseContext)
        var theme = prefs[SharedPrefKeys.APP_THEME.toString(), "Light"]
        if (theme == "Dark" || theme == "डार्क"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        var lan = prefs[SharedPrefKeys.APP_LOCALE.toString(), "English"]
        if (lan == resources.getString(R.string.hindi)) {
            resources.updateConfiguration(conf, dm)
        }
        if(prefs[SharedPrefKeys.ON_BOARDING_DONE.toString(), false] == false){
            startActivity(
                Intent(this, OnBoardingActivity::class.java)
            )
        } else if (prefs[SharedPrefKeys.APP_LOCALE.toString(), ""] == "") {
            startActivity(
                Intent(this, SelectLanguageActivity::class.java)
            )
        } else if (prefs[SharedPrefKeys.APP_THEME.toString(), ""] == "") {
            startActivity(
                Intent(this, SelectThemeActivity::class.java)
            )
        } else if (prefs[SharedPrefKeys.USER_NAME.toString(), ""] == "") {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        } else {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }
}