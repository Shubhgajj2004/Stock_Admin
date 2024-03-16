package com.papercanary.bhimanistockadmin.viewmodels.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.papercanary.bhimanistockadmin.Routes
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.BottomNavBar.BOTTOM_NAV_TITLE1
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.IPOTabs
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.InsuranceTabs


class DashboardViewModel : ViewModel() {
    var selectedItemIndex by mutableIntStateOf(0)
        private set
    var appBarTitle by mutableStateOf(BOTTOM_NAV_TITLE1)
        private set
    var fabText by mutableStateOf(IPOTabs.TAB1)
        private set
    var isFABVisible by mutableStateOf(true)
        private set

    fun onBottomNavigationItemClicked(index: Int, title: String, navController: NavController) {
        selectedItemIndex = index
        appBarTitle = title
        when (index) {
            0 -> {
                navController.navigate(Routes.Dashboard.IPO)
                fabText = IPOTabs.TAB1
                isFABVisible = true
            }

            1 -> {
                navController.navigate(Routes.Dashboard.INSURANCE)
                fabText = InsuranceTabs.TAB1
                isFABVisible = true
            }

            2 -> {
                navController.navigate(Routes.Dashboard.MORE)
                isFABVisible = false
            }
        }
    }

    fun onFABClickListener(dashboardNavController: NavController) {
        when (fabText) {
            IPOTabs.TAB1 -> {
                dashboardNavController.navigate(Routes.ADD_IPO_SCREEN + "/${IPOTabs.TAB1}")
            }
            IPOTabs.TAB2 -> {
                dashboardNavController.navigate(Routes.ADD_IPO_SCREEN + "/${IPOTabs.TAB2}")
            }
            InsuranceTabs.TAB1 -> {
                dashboardNavController.navigate(Routes.ADD_GENERAL_SCREEN + "/${InsuranceTabs.TAB1}")
            }
            InsuranceTabs.TAB2 -> {
                dashboardNavController.navigate(Routes.ADD_GENERAL_SCREEN + "/${InsuranceTabs.TAB2}")
            }
            else -> {

            }
        }
    }

    fun onTabChangeListener(tabTitle: String){
        fabText = tabTitle
    }
}

