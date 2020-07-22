package com.example.upintheair.activity_start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartActivity : AppCompatActivity() {
    val mViewModel: StartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
}