package com.papercanary.bhimanistockadmin.dashboard.dashboardscreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.papercanary.bhimanistockadmin.dashboard.utils.GeneralProfile
import com.papercanary.bhimanistockadmin.viewmodels.insurance.MediclaimViewModel

@Composable
fun MediclaimTabScreen(mediclaimViewModel: MediclaimViewModel) {
    val lazyPagingItems = mediclaimViewModel.getMediclaims().collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems.itemCount){ index ->
            GeneralProfile(generalItem = lazyPagingItems[index]!!)
        }
    }
}