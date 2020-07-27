package com.example.upintheair.activity_global

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.fragment_addwish.AddWishFragment
import com.example.upintheair.fragment_wishlist.WishListFragment
import kotlinx.android.synthetic.main.activity_global.*

class GlobalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global)

        openWishListFragment()

        button_add_wish.setOnClickListener(clickButtonAddWish)
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

    val clickButtonAddWish = object : View.OnClickListener{
        override fun onClick(v: View?) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    AddWishFragment()
                )
                .commit()
        }

    }

}