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
import com.example.registerloginapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup clickable colored text
        setupRegisterText()

        with(binding) {
            btnLogin.setOnClickListener {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    // Simple validation (you can add more complex logic)
                    val intent = Intent(this@LoginActivity, HomepageActivity::class.java)
                    intent.putExtra(MainActivity.EXTRA_USERNAME, username)
                    intent.putExtra(MainActivity.EXTRA_EMAIL, "user@email.com")
                    intent.putExtra(MainActivity.EXTRA_PHONE, "1234567890")
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRegisterText() {
        val fullText = "New Member? Register"
        val spannableString = SpannableString(fullText)

        // Find the position of "Register"
        val startIndex = fullText.indexOf("Register")
        val endIndex = startIndex + "Register".length

        // Make "Register" blue and clickable (sama dengan tombol)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#5A67D8")),
            startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(view: View) {
                // Navigate to register activity
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvRegister.text = spannableString
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
    }
}