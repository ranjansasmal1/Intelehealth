package com.intelehealth.ranjancodetest.data.remote.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Permissions(
    val admin: Boolean? =null,
    val pull: Boolean? =null,
    val push: Boolean? =null
) : Parcelable