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
import androidx.compose.material3.MaterialTheme
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
import com.google.firebase.Timestamp
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables
import com.papercanary.bhimanistockadmin.appconstants.AppDrawables.Dashboard.EDIT_ICON
import com.papercanary.bhimanistockadmin.models.IpoItem
import com.papercanary.bhimanistockadmin.utils.dateFormatter

@Composable
fun IpoProfile(ipoItem: IpoItem) {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                IpoImage(imageUrl = ipoItem.companyImage)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = ipoItem.companyName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(3.dp))
                IconButton(
                    onClick = { /*TODO("Editing IPO details")*/ }) {
                    Icon(
                        painter = painterResource(id = EDIT_ICON),
                        contentDescription = "Share upcoming IPO details",
                        tint = Color.Gray
                    )
                }
            }

            IpoProfileRow(
                key = "Issue Date",
                value = OfferDate(
                    startDate = ipoItem.offerStartDate,
                    endDate = ipoItem.offerEndDate
                ),
                icon = AppDrawables.Dashboard.MainLineIpoProfile.ISSUE_DATE_ICON,
                iconDes = "Issue date icon"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IpoProfileCol(
                    key = "Offer Price",
                    value = "${ipoItem.offerStartPrice}-${ipoItem.offerEndPrice}",
                    icon = AppDrawables.Dashboard.MainLineIpoProfile.OFFER_PRICE_ICON,
                    iconDes = "Rupee icon"
                )
                IpoProfileCol(
                    key = "Lot Size",
                    value = "${ipoItem.lotSize}",
                    icon = AppDrawables.Dashboard.MainLineIpoProfile.LOT_SIZE,
                    iconDes = "Hamburger icon"
                )
//                IpoProfileCol(
//                    key = "GMP",
//                    value = "${ipoItem.gmpStart}-${ipoItem.gmpEnd}",
//                    icon = AppDrawables.Dashboard.MainLineIpoProfile.GMP_ICON,
//                    iconDes = "Issue date icon",
//                    valueColor = Color(0xFF14A44D)
//                )
            }

        }

    }
}

@Composable
private fun IpoProfileRow(key: String, value: String, icon: Int, iconDes: String) {
    Row {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = iconDes,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$key - ",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 14.sp
        )
        Text(text = value, fontSize = 14.sp)
    }
}

@Composable
private fun IpoProfileCol(
    key: String,
    value: String,
    icon: Int,
    iconDes: String,
    valueColor: Color = Color.Unspecified
) {
    Column {
        Row {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = iconDes,
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(text = key, color = Color.Gray, fontSize = 15.sp)
                Text(
                    text = value,
                    color = valueColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }
    }
}

//TODO("Show shimmer effect until image is not loaded")
@Composable
fun IpoImage(
    imageUrl: String,
) {
    Box(modifier = Modifier.size(68.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Uri.parse(imageUrl))
                .crossfade(true)
                .build(),
            contentDescription = "Ipo company logo",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun OfferDate(startDate: Timestamp?, endDate: Timestamp?): String {
    return if (startDate != null && endDate != null) {
        "${dateFormatter(startDate)} - ${dateFormatter(endDate)}"
    } else {
        "To Be Announced"
    }
}