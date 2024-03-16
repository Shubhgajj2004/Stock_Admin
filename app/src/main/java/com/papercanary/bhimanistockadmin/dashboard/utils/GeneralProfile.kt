package com.papercanary.bhimanistockadmin.dashboard.utils

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables
import com.papercanary.bhimanistockadmin.models.GeneralItem


@Composable
fun GeneralProfile(generalItem: GeneralItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 7.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            GeneralImage(imageUrl = generalItem.companyImage)
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier,
                    text = generalItem.companyName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                GeneralProfileRow(
                    key = "Offer Price: ",
                    value = "${generalItem.offerStartPrice}-${generalItem.offerEndPrice}",
                    icon = AppDrawables.Dashboard.MainLineIpoProfile.OFFER_PRICE_ICON,
                    iconDes = "Rupee icon"
                )
            }
            Spacer(modifier = Modifier.width(3.dp))
            IconButton(
                onClick = { /*TODO("Editing IPO details")*/ }) {
                Icon(
                    painter = painterResource(id = AppDrawables.Dashboard.EDIT_ICON),
                    contentDescription = "Share upcoming IPO details",
                    tint = Color.Gray
                )
            }
        }

    }
}

@Composable
private fun GeneralProfileRow(key: String, value: String, icon: Int, iconDes: String) {
    Row {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = iconDes,
            tint = Color.Gray,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Row {
            Text(text = key, color = Color.Gray, fontSize = 14.sp)
            Text(
                text = value,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
        }
    }
}

//TODO("Show shimmer effect until image is not loaded")
@Composable
private fun GeneralImage(
    imageUrl: String,
) {
    Box(modifier = Modifier.size(68.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Uri.parse(imageUrl))
                .crossfade(true)
                .build(),
            contentDescription = "General logo",
            contentScale = ContentScale.Crop,
        )
    }
}
