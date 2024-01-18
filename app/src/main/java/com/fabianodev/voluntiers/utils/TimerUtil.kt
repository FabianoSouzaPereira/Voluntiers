package com.fabianodev.voluntiers.utils

import android.os.Handler
import android.os.Looper
import java.util.Timer
import kotlin.concurrent.schedule

object TimerUtil {
    private val handler = Handler(Looper.getMainLooper())

    fun scheduleTask(interval: Long, task: () -> Unit) {
        val timer = Timer()
        timer.schedule(interval) {
            handler.post(task)
        }
    }
}

