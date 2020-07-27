package com.example.upintheair.fragment_wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upintheair.R
import com.example.upintheair.entity.Wish
import kotlinx.android.synthetic.main.wish_recycler.view.*

class WishListAdapter(
    private val callback: OnItemClick
) : RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {

    val list = mutableListOf<Wish>()

    interface OnItemClick {
        fun OnItemClicked(wishId: Int)
    }

    class WishListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.name_wish
        private val description: TextView = itemView.description_wish

        fun bind(item: Wish) {
            name.text = item.name
            description.text = item.description
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.wish_recycler, parent, false)

        return WishListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        val wish = list[position]
        holder.bind(wish)
        holder.itemView.setOnClickListener {
            callback.OnItemClicked(holder.adapterPosition)
        }
    }
}