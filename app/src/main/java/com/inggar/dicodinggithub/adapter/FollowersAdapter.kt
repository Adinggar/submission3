package com.inggar.dicodinggithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inggar.dicodinggithub.R
import com.inggar.dicodinggithub.entity.UserGithub
import kotlinx.android.synthetic.main.itemrow_user.view.*

class FollowersAdapter(private val listUserGithub: ArrayList<UserGithub>) :
    RecyclerView.Adapter<FollowersAdapter.ListViewHolder>() {
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
        fun bind(userGithub: UserGithub) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(userGithub.avatar)
                    .apply(RequestOptions())
                    .into(item_avatar)
                item_name.text = userGithub.name
                item_username.text = userGithub.username
                item_company.text = userGithub.company
                item_location.text = userGithub.location
            }
        }
    }
}