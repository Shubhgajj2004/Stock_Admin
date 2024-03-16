package com.papercanary.bhimanistockadmin.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

fun dateFormatter(timestamp: Timestamp): String {
    val date = timestamp.toDate()
    val sdf = SimpleDateFormat("dd.MMMM.yyyy", Locale.getDefault())
    return sdf.format(date)
}