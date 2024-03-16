package com.papercanary.bhimanistockadmin.dashboard

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.papercanary.bhimanistockadmin.Routes
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.TopBar.MENU
import com.papercanary.bhimanistockadmin.dashboard.dashboardscreens.InsuranceScreen
import com.papercanary.bhimanistockadmin.dashboard.dashboardscreens.IpoScreen
import com.papercanary.bhimanistockadmin.dashboard.dashboardscreens.MoreScreen
import com.papercanary.bhimanistockadmin.dashboard.models.BottomNavigationItem
import com.papercanary.bhimanistockadmin.dashboard.models.bottomNavItems
import com.papercanary.bhimanistockadmin.viewmodels.dashboard.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(dashboardNavController: NavController) {
    val navController = rememberNavController()
    val dashboardViewModel = viewModel<DashboardViewModel>()

    SetBarColors()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItemIndex = dashboardViewModel.selectedItemIndex,
                onItemClick = { index, title ->
                    dashboardViewModel.onBottomNavigationItemClicked(
                        index = index,
                        title = title,
                        navController
                    )
                },
            )
        },
        topBar = {
            TopBar(
                title = dashboardViewModel.appBarTitle,
            )
        },
        floatingActionButton = {
            if(dashboardViewModel.isFABVisible) {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Add ${dashboardViewModel.fabText}") },
                    icon = {
                        Icon(
                            painter = painterResource(id = AppDrawables.Dashboard.ADD_ICON),
                            contentDescription = "Add button"
                        )
                    },
                    onClick = { dashboardViewModel.onFABClickListener(dashboardNavController) })
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(
                bottom = it.calculateBottomPadding(),
                top = it.calculateTopPadding()
            )
        ) {
            NavHost(
                navController = navController,
                startDestination = Routes.Dashboard.IPO,
                route = "dashboard_graph"
            ) {
                composable(Routes.Dashboard.IPO) {
                    IpoScreen(dashboardViewModel = dashboardViewModel)
                }
                composable(Routes.Dashboard.INSURANCE) {
                    InsuranceScreen(dashboardViewModel = dashboardViewModel)
                }
                composable(Routes.Dashboard.MORE) {
                    MoreScreen(dashboardNavController)
                }
            }
        }
    }

}

@Composable
private fun SetBarColors() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val statusBarColor = MaterialTheme.colorScheme.surface
    val navigationBarColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)

    DisposableEffect(systemUiController, useDarkIcons){
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = useDarkIcons
        )
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )

        onDispose {  }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    title: String,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            Icon(painter = painterResource(id = MENU), contentDescription = "Menu")
        }
    )
}

@Composable
private fun BottomNavigationBar(
    selectedItemIndex: Int,
    onItemClick: (Int, String) -> Unit,
) {
    NavigationBar {
        bottomNavItems.forEachIndexed { index, bottomNavigationItem ->
            val isSelected = selectedItemIndex == index
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onItemClick(index, bottomNavigationItem.title)
                },
                icon = {
                    BottomNavigationItemIcon(
                        isSelected = isSelected,
                        item = bottomNavigationItem
                    )
                },
                label = {
                    Text(text = bottomNavigationItem.title)
                }
            )
        }
    }
}

@Composable
private fun BottomNavigationItemIcon(isSelected: Boolean, item: BottomNavigationItem) {
    val icon = if (isSelected) {
        painterResource(id = item.selectedIcon)
    } else {
        painterResource(id = item.unSelectedIcon)
    }
    Icon(painter = icon, contentDescription = item.title)
}
