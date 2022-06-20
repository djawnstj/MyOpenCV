package com.example.opencv

import android.util.Log

class AppData {
    companion object {

        private const val TAG = "AppData"

        fun debug(tag: String?, msg: String?) = Log.d(tag, msg ?: "msg is null")
        fun err(tag: String?, msg: String?) = Log.e(tag, msg ?: "msg is null")
        fun err(tag: String?, msg: String?, ex: Exception) = Log.e(tag, msg ?: "msg is null", ex)

    }
}