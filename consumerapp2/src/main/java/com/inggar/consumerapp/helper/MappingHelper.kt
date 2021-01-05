package com.inggar.consumerapp.helper

import android.database.Cursor
import com.inggar.consumerapp.database.DatabaseContract
import com.inggar.consumerapp.entity.FavoriteGithub
import java.util.*

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<FavoriteGithub> {
        val favoriteList = ArrayList<FavoriteGithub>()

        notesCursor?.apply {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.USERNAME))
                val name = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.NAME))
                val avatar = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.AVATAR))
                val company = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.COMPANY))
                val location = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.LOCATION))
                val repository = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.REPOSITORY))
                val followers = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.FOLLOWERS))
                val following = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.FOLLOWING))
                val favorite = getString(getColumnIndexOrThrow(DatabaseContract.FavColumns.FAVORITE))

                favoriteList.add(
                    FavoriteGithub(
                        username,
                        name,
                        avatar,
                        company,
                        location,
                        repository,
                        followers,
                        following,
                        favorite
                    )
                )
            }
        }
        return favoriteList
    }
}