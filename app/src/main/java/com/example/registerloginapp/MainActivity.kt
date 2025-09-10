package com.example.registerloginapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.registerloginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup clickable colored text
        setupTermsText()
        setupLoginText()

        with(binding) {
            btnRegister.setOnClickListener {
                val username = edtUsername.text.toString()
                val email = edtEmail.text.toString()
                val phone = edtPhone.text.toString()
                val password = edtPassword.text.toString()

                if (username.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()) {
                    if (cbTerms.isChecked) {
                        val intent = Intent(this@MainActivity, HomepageActivity::class.java)
                        intent.putExtra(EXTRA_USERNAME, username)
                        intent.putExtra(EXTRA_EMAIL, email)
                        intent.putExtra(EXTRA_PHONE, phone)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Please agree to Terms and Conditions", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupTermsText() {
        val fullText = "By checking the box you agree to our Terms and Conditions."
        val spannableString = SpannableString(fullText)

        // Find the position of "Terms and Conditions"
        val startIndex = fullText.indexOf("Terms and Conditions")
        val endIndex = startIndex + "Terms and Conditions".length

        // Make "Terms and Conditions" blue and clickable
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.primary_blue)),
            startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                // Handle Terms and Conditions click
                Toast.makeText(this@MainActivity, "Terms and Conditions clicked", Toast.LENGTH_SHORT).show()
                // You can open a new activity or dialog here to show the terms
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvTerms.text = spannableString
        binding.tvTerms.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupLoginText() {
        val fullText = "Already Have an Account? Log In"
        val spannableString = SpannableString(fullText)

        // Find the position of "Log In"
        val startIndex = fullText.indexOf("Log In")
        val endIndex = startIndex + "Log In".length

        // Make "Log In" blue and clickable (sama dengan tombol)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#5A67D8")),
            startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                // Navigate to login activity
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvLogin.text = spannableString
        binding.tvLogin.movementMethod = LinkMovementMethod.getInstance()
    }
}