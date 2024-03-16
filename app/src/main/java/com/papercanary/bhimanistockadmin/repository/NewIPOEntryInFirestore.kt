package com.papercanary.bhimanistockadmin.repository

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Dashboard.IPOTabs
import com.papercanary.bhimanistockadmin.appconstants.AppConstants.Firestore
import com.papercanary.bhimanistockadmin.models.UploadResult
import com.papercanary.bhimanistockadmin.models.IpoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class NewIPOEntryInFirestore {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun uploadToFirestore(ipoItem: IpoItem, type: String): UploadResult {
        return withContext(Dispatchers.IO) {
            try {
                val collectionRef = if (type == IPOTabs.TAB1) {
                    firestore.collection(Firestore.Collections.MAINLINE_IPOS)
                } else {
                    firestore.collection(Firestore.Collections.SME_IPOS)
                }

                val documentReference = Tasks.await(collectionRef.add(ipoItem))

                UploadResult.Result(true)
            } catch (e: Exception) {
                UploadResult.Result(false)
            }
        }
    }
}