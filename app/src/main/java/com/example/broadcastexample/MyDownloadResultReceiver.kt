package com.example.broadcastexample

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.util.Log

class MyDownloadResultReceiver(handler: Handler?) : ResultReceiver(handler) {


    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {

        super.onReceiveResult(resultCode, resultData)

        if (resultCode == 100 && resultData != null){

            Log.d("service"," completed song = ${resultData.getString("song")}")
            Log.d("service"," on result received method " + Thread.currentThread().name)
//            Log.d("${resultData.getString("song")}" , " ")

            MainActivity.mHandler.post(object:Runnable{
                override fun run() {
                }
            })
        }
    }
}