package com.inggar.consumerapp.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteGithub(
    val username: String? = null,
    val name: String? = null,
    val avatar: String? = null,
    val company: String? = null,
    val location: String? = null,
    val repository: String? = null,
    val followers: String? = null,
    val following: String? = null,
    val isFav: String? = null
) : Parcelable