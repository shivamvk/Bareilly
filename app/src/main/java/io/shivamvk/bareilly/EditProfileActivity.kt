package io.shivamvk.bareilly

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatDelegate
import com.github.dhaval2404.imagepicker.ImagePicker
import io.shivamvk.bareilly.databinding.ActivityEditProfileBinding
import java.lang.IllegalArgumentException

class EditProfileActivity : AppCompatActivity() {

    private val PROFILE_PICTURE_PICKER_CODE = 195
    lateinit var binding: ActivityEditProfileBinding

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
        if (intent.getStringExtra("type").toString() == "Complete profile")
            binding.tvEditProfileText.text = resources.getString(R.string.complete_profile)
        else if (intent.getStringExtra("type").toString() == "Edit profile")
            binding.tvEditProfileText.text = resources.getString(R.string.edit_profile)
        else
            throw IllegalArgumentException("Need a 'type' argument with intent")

        binding.tvPickImage.setOnClickListener {
            ImagePicker.with(this)
                .crop(4f, 4f)
                .compress(3072)
                .start(PROFILE_PICTURE_PICKER_CODE)
        }
        val items = listOf(
            resources.getString(R.string.male),
            resources.getString(R.string.female),
            resources.getString(R.string.other),
            resources.getString(R.string.prefer_not_to_say))
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
}