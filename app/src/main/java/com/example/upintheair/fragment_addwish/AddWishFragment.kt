package com.example.upintheair.fragment_addwish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.upintheair.R
import kotlinx.android.synthetic.main.fragment_addwish.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWishFragment : Fragment() {

    val mViewModel: AddWishViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addwish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add_wish.setOnClickListener(clickOnButtonAddWish)

    }

    val clickOnButtonAddWish = object : View.OnClickListener{
        override fun onClick(v: View?) {



        }

    }
}