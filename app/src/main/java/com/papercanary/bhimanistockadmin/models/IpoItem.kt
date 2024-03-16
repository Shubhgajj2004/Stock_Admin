package com.papercanary.bhimanistockadmin.models

import com.google.firebase.Timestamp

data class IpoItem(
    val companyName: String,
    val companyImage: String,
    val offerStartDate: Timestamp? = null,
    val offerEndDate: Timestamp? = null,
    val offerStartPrice: Int? = null,
    val offerEndPrice: Int? = null,
    val lotSize: Int? = null,
) {
    constructor(): this(
        "",
        "",
    )
}