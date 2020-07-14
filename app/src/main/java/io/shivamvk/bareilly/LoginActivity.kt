package io.shivamvk.bareilly

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import io.shivamvk.bareilly.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper
import io.shivamvk.bareilly.sharedPrefs.SharedPrefKeys
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper.set
import io.shivamvk.bareilly.sharedPrefs.PreferencesHelper.get

/*
    BottomSheetActivity type
    User enters phone number,
    sends an otp and verify's it
    After verifying
        if new user
            send to Edit profile Activity with extra intent "type" as "Complete Profile"
        else if old user
            send to MainActivity
 */
class LoginActivity : AppCompatActivity(), TextWatcher, View.OnClickListener {

    lateinit var binding: ActivityLoginBinding
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PreferencesHelper.appPrefs(this)
        if (prefs[SharedPrefKeys.USER_NAME.toString(), ""] != ""){
            finish()
            startActivity(
                Intent(
                this, MainActivity::class.java
            ))
        }
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.BottomSheetActivityDarkTheme);
        } else {
            setTheme(R.style.BottomSheetActivityLightTheme);
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.etLoginPhone.requestFocus()
        binding.etLoginPhone.addTextChangedListener(this)
        binding.btLoginSendOtp.setOnClickListener(this)
        binding.btLoginVerifyOtp.setOnClickListener(this)
    }

    private fun inputsReceived() {
        binding.etLoginPhone.clearFocus()
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etLoginPhone.windowToken, 0)
        binding.btLoginSendOtp.visibility = View.VISIBLE
    }

    private fun sendOtp() {
        //send a request to server to send otp later
        //and then call otpSent() only if server response is successful
        otpSent()
    }

    private fun otpSent() {
        binding.etLoginPhone.isEnabled = false
        binding.btLoginSendOtp.visibility = View.GONE
        binding.cvLoginOtp.visibility = View.VISIBLE
        binding.llResendOtp.visibility = View.VISIBLE
        var countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onFinish() {
                binding.tvResendOtp.text = resources.getString(R.string.resend_otp)
                binding.tvResendOtp.setOnClickListener(this@LoginActivity)
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.tvResendOtp.text = "${resources.getString(R.string.resend_in)} ${millisUntilFinished/1000} ${resources.getString(R.string.secs)}"
            }

        }.start()
        binding.btLoginVerifyOtp.visibility = View.VISIBLE
    }

    private fun resendOtp() {
        //send a request to server to resend otp later
        //and then call otpSent() to restart the resend timer
        if (tv_resend_otp.text.toString().equals(resources.getString(R.string.resend_otp))) {
            Toast.makeText(applicationContext, resources.getString(R.string.otp_sent_successfuly), Toast.LENGTH_SHORT).show()
            otpSent()
        }
    }

    private fun verifyOTP() {
        if (et_login_otp.text.toString().isEmpty()){
            et_login_otp.requestFocus()
            et_login_otp.error = resources.getString(R.string.enter_a_valid_otp)
        } else {
            //to backend then call next activity
            startActivity(
                Intent(
                    this,
                    EditProfileActivity::class.java
                ).putExtra("type", "Complete profile")
            )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt_login_send_otp -> {
                sendOtp()
            }
            R.id.tv_resend_otp -> {
                resendOtp()
            }
            R.id.bt_login_verify_otp -> {
                verifyOTP()
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString().length != 10){
            binding.etLoginPhone.error = resources.getString(R.string.enter_a_valid_mobile_number)
        } else {
            inputsReceived()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}