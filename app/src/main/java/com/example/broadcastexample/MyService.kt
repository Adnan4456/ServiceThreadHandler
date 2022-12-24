package com.example.broadcastexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

class MyService : Service() {


    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate() {
        super.onCreate()
        Log.d("service","onCreate is called")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val name = intent?.getStringExtra("song")
        Log.d("service","starting download $name" + " startId = $startId")
        name?.let { MySuspendFunction(it , startId) }

        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder? {

        // We don't provide binding, so return null
        return null
    }

    private fun MySuspendFunction(song:String ,startId: Int ){
        scope.launch {

            song.let { download(it , startId) }
        }
    }
    private suspend fun download(song:String , startId: Int){
        try{
            delay(8000)

        }catch(e:InterruptedException){
            e.printStackTrace()
        }
        Log.d("service","complete  download $song" + " startId = $startId")

        stopSelf(startId)
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("service","Service is destory")
//        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
        job.cancel()
    }
}