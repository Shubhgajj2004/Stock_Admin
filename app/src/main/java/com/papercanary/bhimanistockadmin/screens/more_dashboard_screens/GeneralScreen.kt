package com.papercanary.bhimanistockadmin.screens.more_dashboard_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables
import com.papercanary.bhimanistockadmin.dashboard.utils.GeneralProfile
import com.papercanary.bhimanistockadmin.utils.TopBar
import com.papercanary.bhimanistockadmin.viewmodels.GeneralScreenViewModel

@Composable
fun GeneralScreen(dashboardController: NavController, type: String) {
    val generalScreenViewModel = viewModel<GeneralScreenViewModel>()

    Scaffold(
        topBar = {
            TopBar(dashboardController, title = type)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(text = { Text(text = "Add $type") },
                icon = {
                    Icon(
                        painter = painterResource(id = AppDrawables.Dashboard.ADD_ICON),
                        contentDescription = "Add button"
                    )
                },
                onClick = { generalScreenViewModel.onFABClickListener(dashboardController, type) })
        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {
            MainScreen(generalScreenViewModel, type)
        }
    }
}

@Composable
fun MainScreen(generalScreenViewModel: GeneralScreenViewModel, type: String) {
    val collection = if (type == AppConstants.MoreScreen.MORE1) {
        AppConstants.Firestore.Collections.MUTUAL_FUNDS
    } else if(type == AppConstants.MoreScreen.MORE2) {
        AppConstants.Firestore.Collections.PMS
    } else {
        AppConstants.Firestore.Collections.FIXED_INCOME
    }
    val lazyPagingItems =
        generalScreenViewModel.getGenerals(collection = collection).collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems.itemCount) { index ->
            GeneralProfile(generalItem = lazyPagingItems[index]!!)
        }
    }
}