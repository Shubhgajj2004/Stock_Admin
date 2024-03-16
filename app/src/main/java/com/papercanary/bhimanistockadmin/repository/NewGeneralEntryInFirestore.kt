package com.papercanary.bhimanistockadmin.repository

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import com.papercanary.bhimanistockadmin.models.GeneralItem
import com.papercanary.bhimanistockadmin.models.UploadResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class NewGeneralEntryInFirestore {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun uploadToFirestore(generalItem: GeneralItem, type: String): UploadResult {
        return withContext(Dispatchers.IO) {
            try {
                val collectionRef = if (type == AppConstants.Dashboard.InsuranceTabs.TAB1) {
                    firestore.collection(AppConstants.Firestore.Collections.INSURANCES)
                } else if (type == AppConstants.Dashboard.InsuranceTabs.TAB2){
                    firestore.collection(AppConstants.Firestore.Collections.MEDICLAIMS)
                } else if(type == AppConstants.MoreScreen.MORE1) {
                    firestore.collection(AppConstants.Firestore.Collections.MUTUAL_FUNDS)
                } else if(type == AppConstants.MoreScreen.MORE2) {
                    firestore.collection(AppConstants.Firestore.Collections.PMS)
                } else {
                    firestore.collection(AppConstants.Firestore.Collections.FIXED_INCOME)
                }

                val documentReference = Tasks.await(collectionRef.add(generalItem))

                UploadResult.Result(true)
            } catch (e: Exception) {
                UploadResult.Result(false)
            }
        }
    }
}