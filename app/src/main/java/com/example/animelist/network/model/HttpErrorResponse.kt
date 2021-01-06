package com.example.animelist.network.model

import android.os.Message

data class HttpErrorResponse(
    val status:Int,
    val type: String,
    val message: String
    )
