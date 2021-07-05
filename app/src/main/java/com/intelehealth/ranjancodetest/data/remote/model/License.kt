package com.intelehealth.ranjancodetest.data.remote.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class License(
    val key: String? =null,
    val name: String? =null,
    val node_id: String? =null,
    val spdx_id: String? =null,
    val url: String? =null
) : Parcelable