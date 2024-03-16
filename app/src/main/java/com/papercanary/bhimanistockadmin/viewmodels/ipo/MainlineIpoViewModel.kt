package com.papercanary.bhimanistockadmin.viewmodels.ipo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.PAGE_SIZE
import com.papercanary.bhimanistockadmin.models.IpoItem
import com.papercanary.bhimanistockadmin.repository.MainlineIpoPagingSource
import kotlinx.coroutines.flow.Flow

class MainlineIpoViewModel: ViewModel() {
    private val pageSize = PAGE_SIZE

    fun getMainlineIpos(): Flow<PagingData<IpoItem>>{
        return Pager(PagingConfig(pageSize)){
            MainlineIpoPagingSource()
        }.flow.cachedIn(viewModelScope)
    }
}