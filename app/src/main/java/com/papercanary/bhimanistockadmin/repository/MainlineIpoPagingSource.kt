package com.papercanary.bhimanistockadmin.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.papercanary.bhimanistockadmin.models.IpoItem
import com.papercanary.bhimanistockadmin.appconstants.AppConstants
import kotlinx.coroutines.tasks.await

class MainlineIpoPagingSource: PagingSource<DocumentSnapshot, IpoItem>() {
    override fun getRefreshKey(state: PagingState<DocumentSnapshot, IpoItem>): DocumentSnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<DocumentSnapshot>): LoadResult<DocumentSnapshot, IpoItem> {
        return try {
            val currentPage = params.key
            val documents = fetchDocuments(currentPage, params.loadSize)
            val nextPage = documents.lastOrNull()

            LoadResult.Page(
                data = documents.map { it.toObject(IpoItem::class.java)!! },
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e : Exception){
            println("Error during load: $e")
            LoadResult.Error(e)
        }
    }

    private suspend fun fetchDocuments(startAfter: DocumentSnapshot?, pageSize: Int): List<DocumentSnapshot> {
        try {
            val firestore = FirebaseFirestore.getInstance()
            if(startAfter == null){
                val query = firestore.collection(AppConstants.Firestore.Collections.MAINLINE_IPOS)
                    .orderBy(AppConstants.Firestore.IpoS.OFFER_START_DATE, Query.Direction.ASCENDING)
                    .limit(pageSize.toLong())

                val snapshot = query.get().await()
                return snapshot.documents
            } else {
                val query = firestore.collection(AppConstants.Firestore.Collections.MAINLINE_IPOS)
                    .orderBy(
                        AppConstants.Firestore.IpoS.OFFER_START_DATE,
                        Query.Direction.ASCENDING
                    )
                    .startAfter(startAfter)
                    .limit(pageSize.toLong())

                val snapshot = query.get().await()
                println("Snapshot size: ${snapshot.documents.size}")
                return snapshot.documents
            }
        } catch (e: Exception) {
            println("Error fetching documents: $e")
            throw e
        }
    }


}