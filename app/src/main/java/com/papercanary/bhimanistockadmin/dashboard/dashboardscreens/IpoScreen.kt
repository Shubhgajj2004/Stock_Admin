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
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.IPOTabs
import com.papercanary.bhimanistockadmin.dashboard.models.ipoTabItems
import com.papercanary.bhimanistockadmin.viewmodels.dashboard.DashboardViewModel
import com.papercanary.bhimanistockadmin.viewmodels.dashboard.TabViewModel
import com.papercanary.bhimanistockadmin.viewmodels.ipo.MainlineIpoViewModel
import com.papercanary.bhimanistockadmin.viewmodels.ipo.SmeIpoViewModel

@Composable
fun IpoScreen(dashboardViewModel: DashboardViewModel){
    val tabViewModel = viewModel<TabViewModel>()
    val mainlineIpoViewModel = viewModel<MainlineIpoViewModel>()
    val smeIpoViewModel = viewModel<SmeIpoViewModel>()

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        TabRow(selectedTabIndex = tabViewModel.selectedItemIndex) {
            ipoTabItems.forEachIndexed{ index, tabItem ->
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
                MainlineIpoScreen(mainlineIpoViewModel)
                dashboardViewModel.onTabChangeListener(IPOTabs.TAB1)
            }
            1 -> {
                SmeIpoScreen(smeIpoViewModel)
                dashboardViewModel.onTabChangeListener(IPOTabs.TAB2)
            }
        }
    }
}