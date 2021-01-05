package com.inggar.consumerapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inggar.consumerapp.CustomOnItemClickListener
import com.inggar.consumerapp.DetailActivity
import com.inggar.consumerapp.R
import com.inggar.consumerapp.entity.FavoriteGithub
import kotlinx.android.synthetic.main.itemrow_user.view.*
import java.util.*

class FavoriteGithubAdapter(private val activity: Activity) :
    RecyclerView.Adapter<FavoriteGithubAdapter.NoteViewHolder>() {
    var listFavorite = ArrayList<FavoriteGithub>()
        set(listFavorite) {
            if (listFavorite.size > 0) {
                this.listFavorite.clear()
            }
            this.listFavorite.addAll(listFavorite)

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.itemrow_user, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = this.listFavorite.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fav: FavoriteGithub) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(fav.avatar)
                    .apply(RequestOptions().override(55, 55))
                    .into(item_avatar)
                item_name.text = fav.name
                item_username.text = fav.username
                item_company.text = fav.company
                item_location.text = fav.location
                itemView.setOnClickListener(
                    CustomOnItemClickListener(
                        adapterPosition,
                        object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                val intent = Intent(activity, DetailActivity::class.java)
                                intent.putExtra(DetailActivity.EXTRA_POSITION, position)
                                intent.putExtra(DetailActivity.EXTRA_NOTE, fav)
                                activity.startActivity(intent)
                            }
                        }
                    )
                )
            }
        }
    }
}