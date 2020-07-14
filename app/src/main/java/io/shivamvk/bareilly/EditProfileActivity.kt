package io.shivamvk.bareilly

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper.set
import androidx.appcompat.app.AppCompatDelegate
import com.github.dhaval2404.imagepicker.ImagePicker
import io.shivamvk.bareilly.databinding.ActivityEditProfileBinding
import io.shivamvk.bareilly.sharedPrefs.SharedPrefKeys
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.json.JSONObject
import java.lang.IllegalArgumentException

class EditProfileActivity : AppCompatActivity(), TextWatcher {

    private val TAG = "EditProfileActivity"
    private val PROFILE_PICTURE_PICKER_CODE = 195
    lateinit var binding: ActivityEditProfileBinding
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.BottomSheetActivityDarkTheme);
        } else {
            setTheme(R.style.BottomSheetActivityLightTheme);
        }
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        prefs = PreferencesHelper.appPrefs(this)
        init()
    }

    private fun saveProfile(){
        if(validateInputs()){
            var jsonObject = JSONObject()
            jsonObject.put("userName", til_edit_profile_username.editText?.text.toString())
            jsonObject.put("fullName", til_edit_profile_name.editText?.text.toString())
            jsonObject.put("bio", til_edit_profile_bio.editText?.text.toString())
            jsonObject.put("gender", til_edit_profile_gender.editText?.text.toString())
            Log.i(TAG, jsonObject.toString())
            prefs[SharedPrefKeys.USER_NAME.toString()] = til_edit_profile_username.editText?.text.toString()
            startActivity(
                Intent(
                    this, MainActivity::class.java
                )
            )
        }
    }

    private fun validateInputs():Boolean{
        if (binding.tilEditProfileUsername.editText?.text.toString().isEmpty()){
            binding.tilEditProfileUsername.error = resources.getString(R.string.username_is_required_to_proceed)
            binding.tilEditProfileUsername.errorIconDrawable = resources.getDrawable(R.drawable.ic_baseline_error_outline_24)
            return false
        }
        return true
    }

    private fun init() {
        if (intent.getStringExtra("type").toString() == "Complete profile")
            binding.tvEditProfileText.text = resources.getString(R.string.complete_profile)
        else if (intent.getStringExtra("type").toString() == "Edit profile")
            binding.tvEditProfileText.text = resources.getString(R.string.edit_profile)
        else
            throw IllegalArgumentException("Need a 'type' argument with intent")

        binding.tilEditProfileUsername.editText?.addTextChangedListener(this)
        binding.tvPickImage.setOnClickListener {
            ImagePicker.with(this)
                .crop(4f, 4f)
                .compress(3072)
                .start(PROFILE_PICTURE_PICKER_CODE)
        }
        binding.btEditProfileContinue.setOnClickListener {
            saveProfile()
        }
        val items = listOf(
            resources.getString(R.string.male),
            resources.getString(R.string.female),
            resources.getString(R.string.other),
            resources.getString(R.string.prefer_not_to_say)
        )
        val adapter = ArrayAdapter(baseContext, R.layout.gender_list_item, items)
        (binding.tilEditProfileGender.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode) {
                PROFILE_PICTURE_PICKER_CODE -> {
                    binding.ivProfileImage.setImageURI(data?.data)
                }
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().isEmpty()){
            binding.tilEditProfileUsername.error = getString(R.string.username_is_required_to_proceed)
            binding.tilEditProfileUsername.errorIconDrawable = resources.getDrawable(R.drawable.ic_baseline_error_outline_24)
        } else {
            binding.tilEditProfileUsername.error = null
            binding.tilEditProfileUsername.errorIconDrawable = null
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}