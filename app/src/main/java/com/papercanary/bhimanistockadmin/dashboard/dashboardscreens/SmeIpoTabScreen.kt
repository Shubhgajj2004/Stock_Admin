package com.papercanary.bhimanistockadmin.dashboard.dashboardscreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.papercanary.bhimanistockadmin.dashboard.utils.IpoProfile
import com.papercanary.bhimanistockadmin.viewmodels.ipo.SmeIpoViewModel

@Composable
fun SmeIpoScreen(smeIpoViewModel: SmeIpoViewModel) {
    val lazyPagingItems = smeIpoViewModel.getSmeIpos().collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems.itemCount){ index ->
            IpoProfile(ipoItem = lazyPagingItems[index]!!)
        }
    }
}