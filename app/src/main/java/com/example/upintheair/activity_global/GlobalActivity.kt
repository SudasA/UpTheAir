package com.example.upintheair.activity_global

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R

class GlobalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global)

        openWishListFragment()
    }

    fun openWishListFragment() {

    }

}