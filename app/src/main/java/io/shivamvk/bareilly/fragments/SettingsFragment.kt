package io.shivamvk.bareilly.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.shivamvk.bareilly.MainActivity
import io.shivamvk.bareilly.R
import io.shivamvk.bareilly.SelectLanguageActivity
import io.shivamvk.bareilly.SelectThemeActivity
import io.shivamvk.bareilly.databinding.FragmentSettingsBinding
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper
import io.shivamvk.bareilly.sharedPrefs.SharedPrefKeys
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper.get

class SettingsFragment: Fragment(), View.OnClickListener {

    lateinit var binding: FragmentSettingsBinding
    private var SELECT_LANGUAGE_CODE = 391
    private var SELECT_THEME_CODE = 428

    companion object {
        fun newInstance(): Fragment {
            return SettingsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.changeThemeView.setOnClickListener(this)
        binding.changeLanguageView.setOnClickListener(this)
        val prefs = context?.let { PreferencesHelper.appPrefs(it) }
        binding.currentLanguagePref.text =
            context?.resources?.getString(R.string.current_language) + " " +
                    (prefs?.get(SharedPrefKeys.APP_LOCALE.toString(), resources.getString(R.string.english)) ?: null)
        var storedTheme = prefs?.get(SharedPrefKeys.APP_THEME.toString(), "Light")
        if (storedTheme == "Light" || storedTheme == "प्रकाश"){
            storedTheme = resources.getString(R.string.light)
        } else {
            storedTheme = resources.getString(R.string.dark)
        }
        binding.currentThemePref.text =
            context?.resources?.getString(R.string.current_theme) + " " + storedTheme
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.change_language_view -> {
                startActivityForResult(Intent(
                    context,
                    SelectLanguageActivity::class.java
                ), SELECT_LANGUAGE_CODE)
            }
            R.id.change_theme_view -> {
                startActivityForResult(Intent(
                    context,
                    SelectThemeActivity::class.java
                ), SELECT_THEME_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == SELECT_LANGUAGE_CODE) {
                activity?.finish()
                startActivity(
                    Intent(
                    context, MainActivity::class.java
                ).putExtra("openFragment", 3))
            }
            if (requestCode == SELECT_THEME_CODE) {
                activity?.finish()
                startActivity(
                    Intent(
                        context, MainActivity::class.java
                    ).putExtra("openFragment", 3))
            }
        }
    }
}