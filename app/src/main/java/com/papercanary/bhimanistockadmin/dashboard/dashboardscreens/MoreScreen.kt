package com.papercanary.bhimanistockadmin.dashboard.dashboardscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.papercanary.bhimanistockadmin.Routes
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.MoreScreen.MORE1
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.MoreScreen.MORE2
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.MoreScreen.MORE3
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.MoreScreen
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.MoreScreen.ICON1
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.MoreScreen.ICON2
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.MoreScreen.ICON3
import com.papercanary.bhimanistockadmin.screens.more_dashboard_screens.GeneralScreen

@Composable
fun MoreScreen(dashboardNavController: NavController) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        ItemBox(icon = ICON1, title = MORE1, onClick = {
            dashboardNavController.navigate(Routes.GENERAL_SCREEN + "/$MORE1")
        })
        ItemBox(icon = ICON2, title = MORE2, onClick = {
            dashboardNavController.navigate(Routes.GENERAL_SCREEN + "/$MORE2")
        })
        ItemBox(icon = ICON3, title = MORE3, onClick = {
            dashboardNavController.navigate(Routes.GENERAL_SCREEN + "/$MORE3")
        })
    }
}

@Composable
private fun ItemBox(icon: Int, title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 10.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(42.dp)
                    .weight(1f),
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}