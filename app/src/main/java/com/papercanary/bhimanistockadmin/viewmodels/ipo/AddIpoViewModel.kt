package com.papercanary.bhimanistockadmin.viewmodels.ipo

import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.Timestamp
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.AddIpoScreen.EMPTY_ERROR_MSG
import com.papercanary.bhimanistockadmin.models.UploadResult
import com.papercanary.bhimanistockadmin.models.IpoItem
import com.papercanary.bhimanistockadmin.repository.NewIPOEntryInFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@OptIn(ExperimentalMaterial3Api::class)
class AddIpoViewModel : ViewModel() {
    var isUploading by mutableStateOf(false)
        private set
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl
    var imageUrlErrorMsg by mutableStateOf("")
        private set
    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> = _companyName
    var companyNameErrorMsg by mutableStateOf("")
        private set
    private val _lotSize = MutableLiveData<String>()
    val lotSize: LiveData<String> = _lotSize
    var lotSizeErrorMsg by mutableStateOf("")
        private set
    private val _offerStartPrice = MutableLiveData<String>()
    val offerStartPrice: LiveData<String> = _offerStartPrice
    var offerStartPriceErrorMsg by mutableStateOf("")
        private set
    private val _offerEndPrice = MutableLiveData<String>()
    val offerEndPrice: LiveData<String> = _offerEndPrice
    var offerEndPriceErrorMsg by mutableStateOf("")
        private set
    var offerStartDateState =
        DatePickerState(locale = CalendarLocale.ENGLISH, initialDisplayMode = DisplayMode.Input)
        private set
    var offerEndDateState =
        DatePickerState(locale = CalendarLocale.ENGLISH, initialDisplayMode = DisplayMode.Input)
        private set

    fun setImageUrl(imageUrl: String) {
        _imageUrl.value = imageUrl
    }

    fun setCompanyName(companyName: String) {
        _companyName.value = companyName
    }

    fun setLotSize(lotSize: String) {
        _lotSize.value = lotSize
    }

    fun setOfferStartPrice(offerStartPrice: String) {
        _offerStartPrice.value = offerStartPrice
    }

    fun setOfferEndPrice(offerEndPrice: String) {
        _offerEndPrice.value = offerEndPrice
    }

    fun onAddBtnClicked(type: String, navController: NavController) {
        if (checkCompanyName() && checkCompanyImgUrl() && checkLotSize() && checkOfferPrice() && checkOfferDate()) {
            isUploading = true
            val newIpoEntryInFirestore = NewIPOEntryInFirestore()
            val ipoItem = IpoItem(
                companyName = companyName.value!!,
                companyImage = imageUrl.value!!,
                offerStartDate = millisecondsToFirebaseTimestamp(offerStartDateState.selectedDateMillis!!),
                offerEndDate = millisecondsToFirebaseTimestamp(offerEndDateState.selectedDateMillis!!),
                offerStartPrice = offerStartPrice.value!!.toInt(),
                offerEndPrice = offerEndPrice.value!!.toInt(),
                lotSize = lotSize.value!!.toInt()
            )
            GlobalScope.launch(Dispatchers.Main) {
                when (val result = newIpoEntryInFirestore.uploadToFirestore(ipoItem, type = type)) {
                    is UploadResult.Result -> {
                        isUploading = false
                        if (result.isSuccess) {
                            navController.popBackStack()
                        } else {
                            sendToast("Try again!")
                        }
                    }
                }

            }
        }
    }

    private fun sendToast(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }

    private fun millisecondsToFirebaseTimestamp(milliseconds: Long): Timestamp {
        return Timestamp(milliseconds / 1000, ((milliseconds % 1000) * 1000000).toInt())
    }

    private fun checkCompanyName(): Boolean {
        return if (companyName.value.isNullOrBlank()) {
            companyNameErrorMsg = EMPTY_ERROR_MSG
            false
        } else {
            companyNameErrorMsg = ""
            true
        }
    }

    private fun checkCompanyImgUrl(): Boolean {
        return if (imageUrl.value.isNullOrBlank()) {
            imageUrlErrorMsg = EMPTY_ERROR_MSG
            false
        } else {
            imageUrlErrorMsg = ""
            true
        }
    }

    private fun checkLotSize(): Boolean {
        return if (lotSize.value.isNullOrBlank()) {
            lotSizeErrorMsg = EMPTY_ERROR_MSG
            false
        } else {
            lotSizeErrorMsg = ""
            true
        }
    }

    private fun checkOfferPrice(): Boolean {
        if (offerStartPrice.value.isNullOrBlank()) {
            offerStartPriceErrorMsg = EMPTY_ERROR_MSG
            return false
        } else {
            offerStartPriceErrorMsg = ""
        }

        if (offerEndPrice.value.isNullOrBlank()) {
            offerEndPriceErrorMsg = EMPTY_ERROR_MSG
            return false
        } else {
            offerEndPriceErrorMsg = ""
        }
        if (offerStartPrice.value!! > offerEndPrice.value!!) {
            offerStartPriceErrorMsg = "should be less"
            offerEndPriceErrorMsg = "Should be more"
            return false
        } else {
            offerStartPriceErrorMsg = ""
            offerEndPriceErrorMsg = ""
        }

        return true
    }

    private fun checkOfferDate(): Boolean {
        if (offerStartDateState.selectedDateMillis == null) {
            return false
        }
        if (offerEndDateState.selectedDateMillis == null) {
            return false
        }
        if (offerStartDateState.selectedDateMillis!! > offerEndDateState.selectedDateMillis!!) {
            return false
        }

        return true
    }
}