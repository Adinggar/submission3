package com.inggar.consumerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.inggar.consumerapp.adapter.SectionsPagerAdapter
import com.inggar.consumerapp.entity.FavoriteGithub
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_POSITION = "extra_position"
        const val EXTRA_NOTE = "extra_note"
    }

    private lateinit var imageAvatar: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setDataObject()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f

    }


    private fun setDataObject() {
        val favoriteGithub = intent.getParcelableExtra<FavoriteGithub>(EXTRA_NOTE) as FavoriteGithub

        favoriteGithub.name?.let { setActionBarTitle(it) }

        tv_detail_username.text = favoriteGithub.username
        tv_detail_name.text = favoriteGithub.name
        tv_detail_company.text = favoriteGithub.company
        tv_detail_location.text = favoriteGithub.location
        tv_detail_repository.text = favoriteGithub.repository
        tv_detail_following.text = favoriteGithub.following
        tv_detail_followers.text = favoriteGithub.followers

        Glide.with(this)
            .load(favoriteGithub.avatar)
            .into(img_detail_photo)

        imageAvatar = favoriteGithub.avatar.toString()
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }
}