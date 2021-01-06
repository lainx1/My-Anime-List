package com.example.animelist.view.activity;

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.animelist.network.model.*
import com.squareup.moshi.Moshi


open class BaseActivity: AppCompatActivity() {

    fun showloader(loader: LottieAnimationView, loading: Boolean){
        with(loader){
            this.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }


    fun handleApiError(error: ApiError){

        if(error is HttpError){
            val httpErrorResponseAdapter = Moshi.Builder().build().adapter(HttpErrorResponse::class.java)
            val httpErrorResponse = httpErrorResponseAdapter.fromJson(error.body)

        Toast.makeText(this, httpErrorResponse?.message, Toast.LENGTH_SHORT).show()

        }else if(error is NetworkError){
            Toast.makeText(this, error.throwable.message, Toast.LENGTH_SHORT).show()
        }else {
            error as UnknownApiError
        Toast.makeText(this, error.throwable.message,Toast.LENGTH_SHORT).show()
        }
    }
}
