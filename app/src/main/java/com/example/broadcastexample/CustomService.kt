package com.example.broadcastexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.os.ResultReceiver
import android.util.Log

class CustomService : Service() {

    lateinit var mDownloadThread: DownloadThread

    override fun onCreate() {
        super.onCreate()
        mDownloadThread = DownloadThread()
        mDownloadThread.start()

        while (mDownloadThread.mHandler == null)
        {

        }
        Log.d("Custom Service","is created")
        if (mDownloadThread.mHandler == null)
        {
            Log.d("service", "Handler is null")
        }
        mDownloadThread.mHandler?.setDownloadService(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("service","onStartCommand")
        val song = intent?.getStringExtra("song")

        mDownloadThread.mHandler?.setResultReceiver(
            intent?.getParcelableExtra(Intent.EXTRA_RESULT_RECEIVER)!!)

        val msg = Message.obtain()
        msg.obj = song
        msg.arg1 = startId
        mDownloadThread.mHandler?.sendMessage(msg)

        return START_REDELIVER_INTENT
    }

    private fun downloadSong(song: String) {

        Log.d("service", "run: starting download ${song}")

        try{
            Thread.sleep(6000)
        }catch (e: InterruptedException){
            Log.d("service","exception ${e.message}")
        }
        Log.d("service", "completed  download  ${song} ")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Custom service","Service is destory")
    }

}