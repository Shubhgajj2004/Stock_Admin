package com.papercanary.bhimanistockadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.papercanary.bhimanistockadmin.dashboard.DashboardScreen
import com.papercanary.bhimanistockadmin.screens.AddGeneralScreen
import com.papercanary.bhimanistockadmin.screens.AddIpoScreen
import com.papercanary.bhimanistockadmin.screens.more_dashboard_screens.GeneralScreen
import com.papercanary.bhimanistockadmin.ui.theme.BhimaniStockAdminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            BhimaniStockAdminTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.DASHBOARD_SCREEN,
                        route = "root_graph"
                    ) {
                        composable(Routes.DASHBOARD_SCREEN) {
                            DashboardScreen(navController)
                        }
                        composable(Routes.ADD_IPO_SCREEN + "/{type}") {navBackStack ->
                            val type = navBackStack.arguments?.getString("type")
                            AddIpoScreen(navController, type!!)
                        }
                        composable(Routes.ADD_GENERAL_SCREEN + "/{type}") {navBackStack ->
                            val type = navBackStack.arguments?.getString("type")
                            AddGeneralScreen(navController, type!!)
                        }
                        composable(Routes.GENERAL_SCREEN + "/{type}") { navBackStack ->
                            val type = navBackStack.arguments?.getString("type")
                            GeneralScreen(navController, type!!)
                        }
                    }
                }
            }

        }
    }
}