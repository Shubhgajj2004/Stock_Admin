package com.papercanary.bhimanistockadmin.viewmodels.ipo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.models.IpoItem
import com.papercanary.bhimanistockadmin.repository.SmeIpoPagingSource
import kotlinx.coroutines.flow.Flow

class SmeIpoViewModel: ViewModel() {
    private val pageSize = AppConstants.PAGE_SIZE

    fun getSmeIpos(): Flow<PagingData<IpoItem>> {
        return Pager(PagingConfig(pageSize)){
            SmeIpoPagingSource()
        }.flow.cachedIn(viewModelScope)
    }
}