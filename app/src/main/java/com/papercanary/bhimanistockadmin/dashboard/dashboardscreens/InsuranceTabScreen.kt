package com.papercanary.bhimanistockadmin.dashboard.dashboardscreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.papercanary.bhimanistockadmin.dashboard.utils.GeneralProfile
import com.papercanary.bhimanistockadmin.viewmodels.insurance.InsuranceViewModel

@Composable
fun InsuranceTabScreen(insuranceViewModel: InsuranceViewModel) {
    val lazyPagingItems = insuranceViewModel.getInsurances().collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems.itemCount){ index ->
            GeneralProfile(generalItem = lazyPagingItems[index]!!)
        }
    }
}