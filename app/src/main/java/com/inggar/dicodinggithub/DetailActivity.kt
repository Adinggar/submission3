package com.inggar.dicodinggithub

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.inggar.dicodinggithub.adapter.SectionsPagerAdapter
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.AVATAR
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.COMPANY
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.CONTENT_URI
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.FAVORITE
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.FOLLOWERS
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.FOLLOWING
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.LOCATION
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.NAME
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.REPOSITORY
import com.inggar.dicodinggithub.db.DatabaseContract.FavColumns.Companion.USERNAME
import com.inggar.dicodinggithub.db.FavoriteHelper
import com.inggar.dicodinggithub.entity.FavoriteGithub
import com.inggar.dicodinggithub.entity.UserGithub
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_POSITION = "extra_position"
        const val EXTRA_NOTE = "extra_note"
    }

    private var isFavorite = false
    private lateinit var gitHelper: FavoriteHelper
    private var favorites: FavoriteGithub? = null
    private lateinit var imageAvatar: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        gitHelper = FavoriteHelper.getInstance(applicationContext)
        gitHelper.open()

        favorites = intent.getParcelableExtra(EXTRA_NOTE)
        if (favorites != null) {
            setDataObject()
            isFavorite = true
            val checked: Int = R.drawable.favorite
            fab_fav.setImageResource(checked)
        } else {
            setData()
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f

        fab_fav.setOnClickListener(this)
    }

    private fun setData() {
        val userGithub = intent.getParcelableExtra<UserGithub>(EXTRA_USER) as UserGithub

        userGithub.name?.let { setActionBarTitle(it) }

        tv_detail_username.text = userGithub.username
        tv_detail_name.text = userGithub.name
        tv_detail_company.text = userGithub.company
        tv_detail_location.text = userGithub.location
        tv_detail_repository.text = userGithub.repository
        tv_detail_following.text = userGithub.following
        tv_detail_followers.text = userGithub.followers

        Glide.with(this)
            .load(userGithub.avatar)
            .into(img_detail_photo)

        imageAvatar = userGithub.avatar.toString()
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

    override fun onClick(view: View) {
        val checked: Int = R.drawable.favorite
        val unChecked: Int = R.drawable.favorite_border
        if (view.id == R.id.fab_fav) {
            if (isFavorite) {
                gitHelper.deleteById(favorites?.username.toString())
                Toast.makeText(this, getString(R.string.delete_favorite), Toast.LENGTH_SHORT).show()
                fab_fav.setImageResource(unChecked)
                isFavorite = false
            } else {
                val values = ContentValues()
                values.put(USERNAME, tv_detail_username.text.toString())
                values.put(NAME, tv_detail_name.text.toString())
                values.put(COMPANY, tv_detail_company.text.toString())
                values.put(LOCATION, tv_detail_location.text.toString())
                values.put(REPOSITORY, tv_detail_repository.text.toString())
                values.put(FOLLOWERS, tv_detail_followers.text.toString())
                values.put(FOLLOWING, tv_detail_following.text.toString())
                values.put(AVATAR, imageAvatar)
                values.put(FAVORITE, "1")

                isFavorite = true

                contentResolver.insert(CONTENT_URI, values)

                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
                fab_fav.setImageResource(checked)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        gitHelper.close()
    }
}