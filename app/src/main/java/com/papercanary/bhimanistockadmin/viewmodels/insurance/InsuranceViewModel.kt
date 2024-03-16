package com.papercanary.bhimanistockadmin.viewmodels.insurance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.models.GeneralItem
import com.papercanary.bhimanistockadmin.repository.InsurancePagingSource
import kotlinx.coroutines.flow.Flow

class InsuranceViewModel: ViewModel() {
    private val pageSize = AppConstants.PAGE_SIZE

    fun getInsurances(): Flow<PagingData<GeneralItem>> {
        return Pager(PagingConfig(pageSize)){
            InsurancePagingSource()
        }.flow.cachedIn(viewModelScope)
    }
}