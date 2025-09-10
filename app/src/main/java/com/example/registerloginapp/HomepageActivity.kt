package com.example.registerloginapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.registerloginapp.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(MainActivity.EXTRA_USERNAME)
        val email = intent.getStringExtra(MainActivity.EXTRA_EMAIL)
        val phone = intent.getStringExtra(MainActivity.EXTRA_PHONE)

        with(binding) {
            setupWelcomeText(username ?: "User")

            tvEmailStatus.text = "${email ?: "user@email.com"} has been activated"

            tvPhoneStatus.text = "${phone ?: "1234567890"} has been registered"
        }
    }

    private fun setupWelcomeText(username: String) {
        val fullText = "Welcome $username"
        val spannableString = SpannableString(fullText)

        val startIndex = fullText.indexOf(username)
        val endIndex = startIndex + username.length

        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#5A67D8")),
            startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tvWelcomeUser.text = spannableString
    }
}