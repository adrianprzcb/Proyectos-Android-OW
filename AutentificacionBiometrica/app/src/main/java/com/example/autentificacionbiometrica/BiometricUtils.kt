package com.example.autentificacionbiometrica

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationError
import androidx.core.content.ContextCompat

interface BiometricAuthCallback{
    fun onSuccess()
    fun onError()
    fun onNotRecognized()
}
object BiometricUtils{

    fun isDeviceReady(context: Context) : Boolean =
         getCapability(context) == BIOMETRIC_SUCCESS

    fun showPrompt(
        title: String = " Autenticacion Biometrica",
        subtitle: String = " Sí" ,
        description: String = " Introduce tu huella",
        cancelButton: String = " Cancelar",
        activity: AppCompatActivity,
        callback: BiometricAuthCallback
    ){
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setNegativeButtonText(cancelButton)
            .setSubtitle(subtitle)
            .setAllowedAuthenticators(BIOMETRIC_WEAK)
            .build()

        val prompt = initPrompt(activity, callback)
        prompt.authenticate(promptInfo)

    }

    private fun initPrompt(activity: AppCompatActivity , callback: BiometricAuthCallback) : BiometricPrompt{
        val executor = ContextCompat.getMainExecutor(activity)
        val authCallback = object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(
                @AuthenticationError errorCode: Int, errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode , errString)
                callback.onError()
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                callback.onSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                callback.onNotRecognized()
            }
        }
        return BiometricPrompt(activity , executor , authCallback)
    }

    private fun getCapability(context: Context): Int =
        BiometricManager.from(context).canAuthenticate(BIOMETRIC_WEAK)



}