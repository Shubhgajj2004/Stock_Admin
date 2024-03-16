package com.papercanary.bhimanistockadmin.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.models.GeneralItem
import com.papercanary.bhimanistockadmin.models.UploadResult
import com.papercanary.bhimanistockadmin.repository.NewGeneralEntryInFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddGeneralViewModel: ViewModel() {
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
    private val _offerStartPrice = MutableLiveData<String>()
    val offerStartPrice: LiveData<String> = _offerStartPrice
    var offerStartPriceErrorMsg by mutableStateOf("")
        private set
    private val _offerEndPrice = MutableLiveData<String>()
    val offerEndPrice: LiveData<String> = _offerEndPrice
    var offerEndPriceErrorMsg by mutableStateOf("")
        private set

    fun setImageUrl(imageUrl: String) {
        _imageUrl.value = imageUrl
    }

    fun setCompanyName(companyName: String) {
        _companyName.value = companyName
    }

    fun setOfferStartPrice(offerStartPrice: String) {
        _offerStartPrice.value = offerStartPrice
    }

    fun setOfferEndPrice(offerEndPrice: String) {
        _offerEndPrice.value = offerEndPrice
    }

    fun onAddBtnClicked(type: String, navController: NavController) {
        if (checkCompanyName() && checkCompanyImgUrl() && checkOfferPrice()) {
            isUploading = true
            val newGeneralEntryInFirestore = NewGeneralEntryInFirestore()
            val generalItem = GeneralItem(
                companyName = companyName.value!!,
                companyImage = imageUrl.value!!,
                offerStartPrice = offerStartPrice.value!!.toInt(),
                offerEndPrice = offerEndPrice.value!!.toInt(),
            )
            GlobalScope.launch(Dispatchers.Main) {
                when (val result = newGeneralEntryInFirestore.uploadToFirestore(generalItem, type = type)) {
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

    private fun checkCompanyName(): Boolean {
        return if (companyName.value.isNullOrBlank()) {
            companyNameErrorMsg = AppConstants.AddIpoScreen.EMPTY_ERROR_MSG
            false
        } else {
            companyNameErrorMsg = ""
            true
        }
    }

    private fun checkCompanyImgUrl(): Boolean {
        return if (imageUrl.value.isNullOrBlank()) {
            imageUrlErrorMsg = AppConstants.AddIpoScreen.EMPTY_ERROR_MSG
            false
        } else {
            imageUrlErrorMsg = ""
            true
        }
    }

    private fun checkOfferPrice(): Boolean {
        if (offerStartPrice.value.isNullOrBlank()) {
            offerStartPriceErrorMsg = AppConstants.AddIpoScreen.EMPTY_ERROR_MSG
            return false
        } else {
            offerStartPriceErrorMsg = ""
        }

        if (offerEndPrice.value.isNullOrBlank()) {
            offerEndPriceErrorMsg = AppConstants.AddIpoScreen.EMPTY_ERROR_MSG
            return false
        } else {
            offerEndPriceErrorMsg = ""
        }
        if (offerStartPrice.value!!.toInt() > offerEndPrice.value!!.toInt()) {
            offerStartPriceErrorMsg = "should be less"
            offerEndPriceErrorMsg = "Should be more"
            return false
        } else {
            offerStartPriceErrorMsg = ""
            offerEndPriceErrorMsg = ""
        }

        return true
    }
}