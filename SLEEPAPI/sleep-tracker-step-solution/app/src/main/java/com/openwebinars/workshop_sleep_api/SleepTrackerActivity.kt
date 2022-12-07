package com.openwebinars.workshop_sleep_api

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.openwebinars.workshop_sleep_api.databinding.ActivityMainBinding

class SleepTrackerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SleepTrackerActivity"
        const val SLEEP_EVENT = "com.openwebinars.workshop_sleep_api.SLEEP_EVENT"
        const val SLEEP_EVENT_TEST = "com.openwebinars.workshop_sleep_api.SLEEP_EVENT_TEST"
        const val SLEEP_DATA = "SLEEP_DATA"
    }

    private val sleepDataManager = SleepDataManager(this)
    private lateinit var sleepEventsBroadcastReceiver: BroadcastReceiver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermissions()
    }

    private fun checkPermissions() {
        if (isPermissionGranted()) {
            requestSleepTracking()
        } else {
            requestPermission()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACTIVITY_RECOGNITION).not() &&
                grantResults.size == 1 &&
                grantResults[0] == PackageManager.PERMISSION_DENIED) {
            showSettingsDialog(this)
        } else if (requestCode == PERMISSION_REQUEST_ACTIVITY_RECOGNITION &&
                permissions.contains(Manifest.permission.ACTIVITY_RECOGNITION) &&
                grantResults.size == 1 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Log.d(TAG, "Permissions granted")
            requestSleepTracking()
        }
    }

    private fun requestSleepTracking() {
        Log.d(TAG, "Request Sleep Tracking")
        registerSleepUpdateEvents()
        sleepDataManager.subscribe()
    }

    private fun registerSleepUpdateEvents() {
        sleepEventsBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    if (it.action == SLEEP_EVENT) {
                        updateUi(intent.getParcelableExtra(SLEEP_DATA))
                    }
                    if (it.action == SLEEP_EVENT_TEST) {
                        val dataJson = intent.getStringExtra(SLEEP_DATA)
                        val data = Gson().fromJson(dataJson, SleepUiData::class.java)
                        updateUi(data)
                    }
                }
            }
        }
        this.registerReceiver(sleepEventsBroadcastReceiver, IntentFilter(SLEEP_EVENT))
        this.registerReceiver(sleepEventsBroadcastReceiver, IntentFilter(SLEEP_EVENT_TEST))
    }

    private fun updateUi(eventData: SleepUiData?) {
        eventData?.let {
            Log.d(TAG, "Update UI $eventData")
            if (eventData.isSleep()) {
                binding.tvState.text = getText(R.string.sleeping)
            } else {
                binding.tvState.text = getText(R.string.awake)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sleepDataManager.unsubscribe()
        this.unregisterReceiver(sleepEventsBroadcastReceiver)
    }

}