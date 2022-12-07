package com.openwebinars.workshop_sleep_api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SleepUiData(
    val confidence: Int,
    val light: Int,
    val motion: Int
) : Parcelable {

    companion object {
        private const val CONFIDENCE_THRESHOLD = 50
        private const val LIGHT_THRESHOLD = 3
        private const val MOTION_THRESHOLD = 1
    }

    fun isSleep(): Boolean =
        (confidence > CONFIDENCE_THRESHOLD && light < LIGHT_THRESHOLD && motion <= MOTION_THRESHOLD)

}
