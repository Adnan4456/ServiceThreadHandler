package com.example.broadcastexample

import android.os.*
import android.util.Log


class DownloadHandler : Handler(Looper.getMainLooper()) {

    lateinit var myService: CustomService
    lateinit var mResultReceiver: ResultReceiver


    override fun handleMessage(msg: Message) {
        downloadSong(msg.obj.toString())

        myService.stopSelf(msg.arg1)

        val bundel = Bundle()
        bundel.putString("song",msg.obj.toString())
        mResultReceiver.send(100 , bundel)
     }

    private fun downloadSong(songName: String) {

        Log.d("service", "run: starting download");

        try {

            Thread.sleep(4000)
        }
        catch (e: InterruptedException)
        {
            // Restore interrupt status.
            Thread.currentThread().interrupt()
//            Log.e("service" , "Exception occur ${e.message}")
        }

        Log.d("service", "downloadSong: "+songName+" Downloaded...");
    }

     fun setDownloadService(service: CustomService){
        this.myService = service
    }
    fun setResultReceiver(resultReceiver: ResultReceiver){
        this.mResultReceiver = resultReceiver
    }
}