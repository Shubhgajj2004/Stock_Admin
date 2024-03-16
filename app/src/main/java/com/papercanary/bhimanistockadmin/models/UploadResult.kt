package com.papercanary.bhimanistockadmin.models

sealed class UploadResult {
    data class Result(val isSuccess: Boolean): UploadResult()
}
