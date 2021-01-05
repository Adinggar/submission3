package com.inggar.consumerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inggar.consumerapp.R
import com.inggar.consumerapp.entity.UserGithub
import kotlinx.android.synthetic.main.itemrow_user.view.*


class FollowingAdapter(private val listUserGithub: ArrayList<UserGithub>) :
    RecyclerView.Adapter<FollowingAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.itemrow_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUserGithub[position])
    }

    override fun getItemCount(): Int = listUserGithub.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserGithub) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions())
                    .into(item_avatar)
                item_name.text = user.name
                item_username.text = user.username
                item_company.text = user.company
                item_location.text = user.location
            }
        }
    }
}