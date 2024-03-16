package com.papercanary.bhimanistockadmin.dashboard.dashboardscreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.firebase.Timestamp
import com.papercanary.bhimanistockadmin.dashboard.utils.IpoProfile
import com.papercanary.bhimanistockadmin.models.IpoItem
import com.papercanary.bhimanistockadmin.viewmodels.ipo.MainlineIpoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

val dateFormat = SimpleDateFormat("dd.MMMM.yyyy", Locale.getDefault())
val ipo1 = IpoItem(
    companyName = "Jana Small Finance Bank Ltd.",
    companyImage = "https://www.chittorgarh.com/images/ipo/Jana-SFB-logo.png",
    offerStartDate = Timestamp(dateFormat.parse("20.Jan.2022")!!),
    offerEndDate = Timestamp(dateFormat.parse("22.Jan.2022")!!),
    offerStartPrice = 1488,
    offerEndPrice = 1490,
    lotSize = 10,
)

@Composable
fun MainlineIpoScreen(mainlineIpoViewModel: MainlineIpoViewModel) {
    val lazyPagingItems = mainlineIpoViewModel.getMainlineIpos().collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems.itemCount){ index ->
            IpoProfile(ipoItem = lazyPagingItems[index]!!)
        }
    }
//    IpoProfile(ipo1)
}

@Preview(showBackground = true)
@Composable
fun PreviewIpoProfile() {
    IpoProfile(ipo1)
}