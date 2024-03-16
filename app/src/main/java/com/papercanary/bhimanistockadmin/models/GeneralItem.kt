package com.papercanary.bhimanistockadmin.models

data class GeneralItem(
    val companyName: String,
    val companyImage: String,
    val offerStartPrice: Int? = null,
    val offerEndPrice: Int? = null,
) {
    constructor(): this(
        "",
        "",
    )
}