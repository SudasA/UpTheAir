package com.example.upintheair.fragment_addwish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.upintheair.R
import com.example.upintheair.fragment_wishlist.WishListFragment
import kotlinx.android.synthetic.main.activity_global.*
import kotlinx.android.synthetic.main.fragment_addwish.*
import kotlinx.android.synthetic.main.fragment_addwish.button_add_wish
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

        if (activity != null) {
            mViewModel.loadingLiveData.observe(activity!!, Observer<Boolean> {
                when (it) {
                    false -> {
                        progress_bar.visibility = View.GONE
                        button_add_wish.visibility = View.VISIBLE
                        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    }
                    true -> {
                        activity!!.window.setFlags(
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        )
                        button_add_wish.visibility = View.INVISIBLE
                        progress_bar.visibility = View.VISIBLE
                    }
                }
            })

            mViewModel.goToWishListFragment.observe(activity!!, Observer<Boolean> {
                if (it) {
                    openWishListFragment()
                }
            })
        }

    }

    val clickOnButtonAddWish = object : View.OnClickListener {
        override fun onClick(v: View?) {
            mViewModel.sendWish(
                edit_text_name_wish.text.toString(),
                edit_text_description_wish.text.toString()
            )
        }
    }

    fun openWishListFragment() {
        if (activity != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    WishListFragment()
                )
                .commit()
        }
    }
}