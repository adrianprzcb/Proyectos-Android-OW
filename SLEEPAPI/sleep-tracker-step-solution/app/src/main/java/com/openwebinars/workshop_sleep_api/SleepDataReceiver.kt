package com.openwebinars.workshop_sleep_api

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.SleepClassifyEvent
import com.openwebinars.workshop_sleep_api.SleepTrackerActivity.Companion.SLEEP_DATA
import com.openwebinars.workshop_sleep_api.SleepTrackerActivity.Companion.SLEEP_EVENT

class SleepDataReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "SleepDataReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (SleepClassifyEvent.hasEvents(intent)) {
                val events = SleepClassifyEvent.extractEvents(intent)
                Log.d(TAG, "SleepClassifyEvents")
                for (event in events) {
                    Log.d(
                        TAG,
                        "Confidence: ${event.confidence} - Light: ${event.light} - Motion: ${event.motion}"
                    )
                    context?.let {
                        sendEventToUi(context, event)
                    }
                }
            }
        }
    }

    private fun sendEventToUi(context: Context, event: SleepClassifyEvent) {
        val intent = Intent(SLEEP_EVENT).apply {
            putExtra(SLEEP_DATA, event.toUiEvent())
        }
        context.sendBroadcast(intent)
    }

    private fun SleepClassifyEvent.toUiEvent() =
        SleepUiData(
            confidence = this.confidence,
            light = this.light,
            motion = this.motion
        )
}