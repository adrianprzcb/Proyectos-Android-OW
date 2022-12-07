package com.openwebinars.workshop_sleep_api

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.SleepSegmentRequest

class SleepDataManager(private val context: Context) {

    fun subscribe() {
        ActivityRecognition.getClient(context)
            .requestSleepSegmentUpdates(buildReceiverPendingIntent(),
                SleepSegmentRequest.getDefaultSleepSegmentRequest())
    }

    fun unsubscribe() {
        ActivityRecognition.getClient(context)
            .removeSleepSegmentUpdates(buildReceiverPendingIntent())
    }

    private fun buildReceiverPendingIntent(): PendingIntent {
        val intent = Intent(context, SleepDataReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
    }
}