package com.papercanary.bhimanistockadmin.dashboard.models

import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.BottomNavBar.BOTTOM_NAV_TITLE1
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.BottomNavBar.BOTTOM_NAV_TITLE2
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.BottomNavBar.BOTTOM_NAV_TITLE3
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.BottomNavBar.SELECTED_ICON1
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.BottomNavBar.SELECTED_ICON2
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.BottomNavBar.SELECTED_ICON3
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.BottomNavBar.UNSELECTED_ICON1
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.BottomNavBar.UNSELECTED_ICON2
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.BottomNavBar.UNSELECTED_ICON3

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
)

val bottomNavItems = listOf(
    BottomNavigationItem(
        title = BOTTOM_NAV_TITLE1,
        selectedIcon = SELECTED_ICON1,
        unSelectedIcon = UNSELECTED_ICON1
    ),
    BottomNavigationItem(
        title = BOTTOM_NAV_TITLE2,
        selectedIcon = SELECTED_ICON2,
        unSelectedIcon = UNSELECTED_ICON2
    ),
    BottomNavigationItem(
        title = BOTTOM_NAV_TITLE3,
        selectedIcon = SELECTED_ICON3,
        unSelectedIcon = UNSELECTED_ICON3
    ),
)