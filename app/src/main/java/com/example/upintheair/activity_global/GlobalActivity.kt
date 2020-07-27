package com.example.upintheair.activity_global

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.fragment_wishlist.WishListFragment

class GlobalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global)

        openWishListFragment()
    }

    fun openWishListFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                WishListFragment()
            )
            .commit()
    }

}