package com.papercanary.bhimanistockadmin.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.AddIpoScreen
import com.papercanary.bhimanistockadmin.dashboard.utils.OutlineInputField
import com.papercanary.bhimanistockadmin.utils.TopBar
import com.papercanary.bhimanistockadmin.viewmodels.ipo.AddIpoViewModel

@Composable
fun AddIpoScreen(navController: NavController, type: String) {
    val addIpoViewModel = viewModel<AddIpoViewModel>()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        addIpoViewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(topBar = {
        TopBar(navController, title = "Add new $type IPO")
    }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {
            MainScreen(addIpoViewModel, type, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    addIpoViewModel: AddIpoViewModel,
    type: String,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val isUploading = addIpoViewModel.isUploading
    val companyName = addIpoViewModel.companyName.observeAsState("")
    val imageUrl = addIpoViewModel.imageUrl.observeAsState("")
    val lotSize = addIpoViewModel.lotSize.observeAsState("")
    val offerStartPrice = addIpoViewModel.offerStartPrice.observeAsState("")
    val offerEndPrice = addIpoViewModel.offerEndPrice.observeAsState("")
    val offerStartDate = addIpoViewModel.offerStartDateState
    val offerEndDate = addIpoViewModel.offerEndDateState

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
                addIpoViewModel.setCompanyName(it)
            }, label = AddIpoScreen.COMPANY_NAME,
            errorMessage = addIpoViewModel.companyNameErrorMsg
        )

        OutlineInputField(
            value = imageUrl.value, onValueChange = {
                addIpoViewModel.setImageUrl(it)
            }, label = AddIpoScreen.COMPANY_IMAGE_URL,
            errorMessage = addIpoViewModel.imageUrlErrorMsg
        )

        OutlineInputField(
            value = lotSize.value, onValueChange = {
                addIpoViewModel.setLotSize(it)
            }, label = AddIpoScreen.LOT_SIZE,
            isNumber = true,
            errorMessage = addIpoViewModel.lotSizeErrorMsg
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                OutlineInputField(
                    value = offerStartPrice.value, onValueChange = {
                        addIpoViewModel.setOfferStartPrice(it)
                    }, label = AddIpoScreen.OFFER_START_PRICE,
                    isNumber = true,
                    errorMessage = addIpoViewModel.offerStartPriceErrorMsg
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                OutlineInputField(
                    value = offerEndPrice.value, onValueChange = {
                        addIpoViewModel.setOfferEndPrice(it)
                    }, label = AddIpoScreen.OFFER_END_PRICE,
                    isNumber = true,
                    errorMessage = addIpoViewModel.offerEndPriceErrorMsg
                )
            }
        }

        DatePicker(state = offerStartDate, showModeToggle = false, title = {
            Text(text = AddIpoScreen.START_DATE_TITLE)
        })

        DatePicker(state = offerEndDate, showModeToggle = false, title = {
            Text(text = AddIpoScreen.END_DATE_TITLE)
        })

        Button(
            onClick = { addIpoViewModel.onAddBtnClicked(type, navController) },
            modifier = Modifier.fillMaxWidth()
        ) {
            if(isUploading){
                CircularProgressIndicator()
            } else {
                Text(text = AddIpoScreen.ADD_IPO)
            }
        }

    }
}





