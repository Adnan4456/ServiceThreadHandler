package com.example.broadcastexample

import android.os.Looper

class DownloadThread: Thread() {

    var mHandler: DownloadHandler? = null

    override fun run() {

        mHandler = DownloadHandler()

    }
}