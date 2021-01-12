package com.example.animelist.network.model.`interface`

import com.example.animelist.network.model.HttpErrorResponse
import com.example.animelist.network.model.NetworkError
import com.example.animelist.network.model.UnknownApiError

interface HandleErrors {

    fun onHttpError(httpErrorResponse: HttpErrorResponse)

    fun onNetworkError(throwable: Throwable)

    fun unknownApiError(throwable: Throwable)
}