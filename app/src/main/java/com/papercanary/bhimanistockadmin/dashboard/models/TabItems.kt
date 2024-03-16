package com.papercanary.bhimanistockadmin.dashboard.models


import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables


data class TabItem(
    val title: String,
    val icon: Int,
)

val ipoTabItems = listOf(
    TabItem(
        title = AppConstants.Dashboard.IPOTabs.TAB1,
        icon = AppDrawables.Dashboard.IPOTabs.TAB_ICON1
    ),
    TabItem(
        title = AppConstants.Dashboard.IPOTabs.TAB2,
        icon = AppDrawables.Dashboard.IPOTabs.TAB_ICON2
    ),
)

val insuranceTabItems = listOf(
    TabItem(
        title = AppConstants.Dashboard.InsuranceTabs.TAB1,
        icon = AppDrawables.Dashboard.InsuranceTabs.TAB_ICON1
    ),
    TabItem(
        title = AppConstants.Dashboard.InsuranceTabs.TAB2,
        icon = AppDrawables.Dashboard.InsuranceTabs.TAB_ICON2
    ),
)