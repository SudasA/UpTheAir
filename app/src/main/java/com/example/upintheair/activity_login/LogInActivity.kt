package com.example.upintheair.activity_login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInActivity : AppCompatActivity() {

    val vm: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}