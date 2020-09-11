package com.example.upintheair.fragment_wishlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upintheair.R
import com.example.upintheair.activity_wish.WishActivity
import com.example.upintheair.entity.Wish
import com.example.upintheair.fragment_addwish.AddWishFragment
import kotlinx.android.synthetic.main.fragment_wishlist.*
import kotlinx.android.synthetic.main.fragment_wishlist.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishListFragment : Fragment() {

    val mViewModel: WishListViewModel by viewModel()
    lateinit var wishListAdapter: WishListAdapter

    private val observeWishList = Observer<MutableList<Wish>> { list ->
        wishListAdapter.list.addAll(list)
        wishListAdapter.notifyDataSetChanged()
    }

    private val observeEmptyWishList = Observer<Boolean> {
        when (it) {
            true -> {
                text_empty_wish_list.visibility = View.VISIBLE
            }
            false -> {
                text_empty_wish_list.visibility = View.GONE
            }
        }
    }

    val clickButtonAddWish = object : View.OnClickListener {
        override fun onClick(v: View?) {
            openAddWishFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_wishlist, container, false)

        wishListAdapter = WishListAdapter(OnItemClick)
        rootView.recyclerview_wish_list.layoutManager = LinearLayoutManager(activity)
        rootView.recyclerview_wish_list.adapter = wishListAdapter

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_add_wish.setOnClickListener(clickButtonAddWish)

        mViewModel.getWishList()
        mViewModel.wishList.observe(viewLifecycleOwner, observeWishList)
        mViewModel.emptyWishList.observe(viewLifecycleOwner, observeEmptyWishList)
    }

    val OnItemClick = object : WishListAdapter.OnItemClick {
        override fun OnItemClicked(wishId: Int) {
            val intent = Intent(activity, WishActivity::class.java)
            intent.putExtra("WISH_ID_KEY", wishId)
            startActivity(intent)
        }
    }

    fun openAddWishFragment() {
        if (activity != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    AddWishFragment()
                )
                .addToBackStack("addWish")
                .commit()
        }
    }
}
