package com.example.upintheair.fragment_wish

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.upintheair.R
import com.example.upintheair.activity_global.GlobalActivity
import com.example.upintheair.entity.Wish
import kotlinx.android.synthetic.main.fragment_wish.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishFragment: Fragment() {

    private val vm: WishViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val id = arguments!!.getInt("WISH_ID_KEY", -1)
            vm.getWish(id)
            if (activity != null)
            vm.wish.observe(activity!!, observerWish)
        }

        button_delete_wish.setOnClickListener(clickOnButtonDeleteWish)
    }

    private val observerWish = Observer<Wish> {
        text_name_wish.text = it.name
        text_description_wish.text = it.description
    }

    private val clickOnButtonDeleteWish = View.OnClickListener {
        if (vm.wish.value != null)
            vm.deleteWish(vm.wish.value!!.id!!)

        if (activity != null) {
            val intent = Intent(activity, GlobalActivity::class.java)
            startActivity(intent)
        }
    }
}