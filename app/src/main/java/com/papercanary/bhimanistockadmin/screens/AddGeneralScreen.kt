package com.papercanary.bhimanistockadmin.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.dashboard.utils.OutlineInputField
import com.papercanary.bhimanistockadmin.utils.TopBar
import com.papercanary.bhimanistockadmin.viewmodels.AddGeneralViewModel
import com.papercanary.bhimanistockadmin.viewmodels.ipo.AddIpoViewModel

@Composable
fun AddGeneralScreen(navController: NavController, type: String) {
    val addGeneralViewModel = viewModel<AddGeneralViewModel>()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        addGeneralViewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(topBar = {
        TopBar(navController, title = "Add new $type")
    }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {
            MainScreen(addGeneralViewModel, type, navController)
        }
    }
}

@Composable
private fun MainScreen(
    addGeneralViewModel: AddGeneralViewModel,
    type: String,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val isUploading = addGeneralViewModel.isUploading
    val companyName = addGeneralViewModel.companyName.observeAsState("")
    val imageUrl = addGeneralViewModel.imageUrl.observeAsState("")
    val offerStartPrice = addGeneralViewModel.offerStartPrice.observeAsState("")
    val offerEndPrice = addGeneralViewModel.offerEndPrice.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlineInputField(
            value = companyName.value, onValueChange = {
                addGeneralViewModel.setCompanyName(it)
            }, label = AppConstants.AddIpoScreen.COMPANY_NAME,
            errorMessage = addGeneralViewModel.companyNameErrorMsg
        )

        OutlineInputField(
            value = imageUrl.value, onValueChange = {
                addGeneralViewModel.setImageUrl(it)
            }, label = AppConstants.AddIpoScreen.COMPANY_IMAGE_URL,
            errorMessage = addGeneralViewModel.imageUrlErrorMsg
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                OutlineInputField(
                    value = offerStartPrice.value, onValueChange = {
                        addGeneralViewModel.setOfferStartPrice(it)
                    }, label = AppConstants.AddIpoScreen.OFFER_START_PRICE,
                    isNumber = true,
                    errorMessage = addGeneralViewModel.offerStartPriceErrorMsg
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                OutlineInputField(
                    value = offerEndPrice.value, onValueChange = {
                        addGeneralViewModel.setOfferEndPrice(it)
                    }, label = AppConstants.AddIpoScreen.OFFER_END_PRICE,
                    isNumber = true,
                    errorMessage = addGeneralViewModel.offerEndPriceErrorMsg
                )
            }
        }

        Button(
            onClick = { addGeneralViewModel.onAddBtnClicked(type, navController) },
            modifier = Modifier.fillMaxWidth()
        ) {
            if(isUploading){
                CircularProgressIndicator()
            } else {
                Text(text = AppConstants.AddGeneral.ADD_BUTTON)
            }
        }

    }
}