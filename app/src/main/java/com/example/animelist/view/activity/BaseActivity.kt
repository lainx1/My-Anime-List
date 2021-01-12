package com.example.animelist.view.activity

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.animelist.network.model.*
import com.example.animelist.network.model.`interface`.HandleErrors
import com.squareup.moshi.Moshi

open class BaseActivity: AppCompatActivity() {

    fun showLoader(loader: LottieAnimationView, loading: Boolean){
        with(loader){
            this.visibility = if(loading) View.VISIBLE else View.GONE
        }
    }

    fun handleApiError(error: ApiError, handleErrors: HandleErrors? = null){

        if(error is HttpError){

            val httpErrorResponseAdapter = Moshi.Builder().build().adapter(HttpErrorResponse::class.java)
            val httpErrorResponse = httpErrorResponseAdapter.fromJson(error.body)

            if(handleErrors != null){
                handleErrors.onHttpError(httpErrorResponse = httpErrorResponse!!)
                return
            }

            //This is the default behavior
            Toast.makeText(this, httpErrorResponse?.message, Toast.LENGTH_SHORT).show()


        }else if(error is NetworkError){

            if(handleErrors != null){
                handleErrors.onNetworkError(throwable = error.throwable)
                return
            }

            //This is the default behavior
            Toast.makeText(this, error.throwable.message, Toast.LENGTH_SHORT).show()
            //TODO show network fail activity

        }else{

            error as UnknownApiError

            if(handleErrors != null){
                handleErrors.onNetworkError(throwable = error.throwable)
                return
            }


            //This is the default behavior
            Toast.makeText(this, error.throwable.message, Toast.LENGTH_SHORT).show()
            //TODO show unknown error activity

        }
    }

}