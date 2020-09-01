package com.example.upintheair.activity_start

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.activity_global.GlobalActivity
import com.example.upintheair.activity_login.LogInActivity
import com.example.upintheair.isOnline
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartActivity : AppCompatActivity() {
    val mViewModel: StartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        val user = false
        routeToAppropriatePage(user)
        finish()
    }

    private fun routeToAppropriatePage(user: Boolean) {
        when (user) {
            true -> startActivity(Intent(this, GlobalActivity::class.java))
            false -> {
                if (isOnline(this))
                    startActivity(Intent(this, LogInActivity::class.java))
                else {
                    Toast.makeText(this, resources.getText(R.string.error_dont_have_internet_connection), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}