package com.example.upintheair.activity_wish

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.upintheair.R
import com.example.upintheair.activity_global.GlobalActivity
import com.example.upintheair.entity.Wish
import kotlinx.android.synthetic.main.activity_wish.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishActivity : AppCompatActivity() {

    val mViewModel: WishViewModel by viewModel()

    private val observer = Observer<Wish> {
        text_name_wish.text = it.name
        text_description_wish.text = it.description
    }

    val clickOnButtonDeleteWish = object : View.OnClickListener {
        override fun onClick(v: View?) {
            if (mViewModel.wish.value != null)
                mViewModel.deleteWish(mViewModel.wish.value!!.id!!)

            val intent = Intent(this@WishActivity, GlobalActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish)

        if (intent != null) {
            val id = intent!!.getIntExtra("WISH_ID_KEY", -1)
            mViewModel.getWish(id)
            mViewModel.wish.observe(this, observer)

        }

        button_delete_wish.setOnClickListener(clickOnButtonDeleteWish)
    }
}