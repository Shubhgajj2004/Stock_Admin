package com.papercanary.bhimanistockadmin.dashboard.dashboardscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.dashboard.models.insuranceTabItems
import com.papercanary.bhimanistockadmin.viewmodels.dashboard.DashboardViewModel
import com.papercanary.bhimanistockadmin.viewmodels.dashboard.TabViewModel
import com.papercanary.bhimanistockadmin.viewmodels.insurance.InsuranceViewModel
import com.papercanary.bhimanistockadmin.viewmodels.insurance.MediclaimViewModel


@Composable
fun InsuranceScreen(dashboardViewModel: DashboardViewModel){
    val tabViewModel = viewModel<TabViewModel>()
    val insuranceViewModel = viewModel<InsuranceViewModel>()
    val mediclaimViewModel = viewModel<MediclaimViewModel>()

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        TabRow(selectedTabIndex = tabViewModel.selectedItemIndex) {
            insuranceTabItems.forEachIndexed{ index, tabItem ->
                Tab(
                    selected = index == tabViewModel.selectedItemIndex,
                    text = {
                        Text(text = tabItem.title)
                    },
                    icon = {
                        Icon(painter = painterResource(id = tabItem.icon), contentDescription = tabItem.title)
                    },
                    onClick = {
                        tabViewModel.onItemClick(index= index)
                    },
                )
            }
        }
        when(tabViewModel.selectedItemIndex){
            0 -> {
                InsuranceTabScreen(insuranceViewModel = insuranceViewModel)
                dashboardViewModel.onTabChangeListener(AppConstants.Dashboard.InsuranceTabs.TAB1)
            }
            1 -> {
                MediclaimTabScreen(mediclaimViewModel = mediclaimViewModel)
                dashboardViewModel.onTabChangeListener(AppConstants.Dashboard.InsuranceTabs.TAB2)
            }
        }
    }
}