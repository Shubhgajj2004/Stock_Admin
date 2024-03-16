package com.papercanary.bhimanistockadmin.viewmodels.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TabViewModel: ViewModel() {
    var selectedItemIndex by mutableIntStateOf(0)
        private set

    fun onItemClick(index: Int){
        selectedItemIndex = index
    }
}