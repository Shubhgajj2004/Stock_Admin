package com.papercanary.bhimanistockadmin.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.papercanary.bhimanistockadmin.Routes
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.models.GeneralItem
import com.papercanary.bhimanistockadmin.repository.GeneralPagingSource
import com.papercanary.bhimanistockadmin.repository.InsurancePagingSource
import kotlinx.coroutines.flow.Flow

class GeneralScreenViewModel: ViewModel() {
    private val pageSize = AppConstants.PAGE_SIZE

    fun getGenerals(collection: String): Flow<PagingData<GeneralItem>> {
        return Pager(PagingConfig(pageSize)){
            GeneralPagingSource(collection = collection)
        }.flow.cachedIn(viewModelScope)
    }

    fun onFABClickListener(dashboardNavController: NavController, type: String) {
        when (type) {
            AppConstants.MoreScreen.MORE1 -> {
                dashboardNavController.navigate(Routes.ADD_GENERAL_SCREEN + "/${AppConstants.MoreScreen.MORE1}")
            }
            AppConstants.MoreScreen.MORE2 -> {
                dashboardNavController.navigate(Routes.ADD_GENERAL_SCREEN + "/${AppConstants.MoreScreen.MORE2}")
            }
            AppConstants.MoreScreen.MORE3 -> {
                dashboardNavController.navigate(Routes.ADD_GENERAL_SCREEN + "/${AppConstants.MoreScreen.MORE3}")
            }
            else -> {

            }
        }
    }
}