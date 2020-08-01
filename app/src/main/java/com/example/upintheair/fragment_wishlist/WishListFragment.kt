package com.example.upintheair.fragment_wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upintheair.R
import com.example.upintheair.entity.Wish
import kotlinx.android.synthetic.main.fragment_wishlist.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishListFragment : Fragment() {

    val mViewModel: WishListViewModel by viewModel()
    lateinit var wishListAdapter: WishListAdapter

    private val observe = Observer<List<Wish>>{list ->
        wishListAdapter.list.addAll(list)
        wishListAdapter.notifyDataSetChanged()
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

        mViewModel.getWishList()

        mViewModel.wishListData.observe(viewLifecycleOwner, observe)

    }

    val OnItemClick = object : WishListAdapter.OnItemClick {
        override fun OnItemClicked(wishId: Int) {

        }

    }
}