package com.example.autentificacionbiometrica

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , BiometricAuthCallback {

    companion object {
        private const val USER_DATA = "user_data"
        private const val NAME = "Name"
        private const val MAIL = "Mail"
        private const val PHONE = "Phone"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBiometricCapability()
        showBiometricPrompt()
        fillUserData()
        btnSave.setOnClickListener {saveUserData()}

    }

    override fun onSuccess() {
        layout.visibility = View.VISIBLE
    }

    override fun onError() {
        Toast.makeText(this, "Error no esperado" , Toast.LENGTH_LONG).show()
    }

    override fun onNotRecognized() {
        Toast.makeText(this, "Huella no valida" , Toast.LENGTH_LONG).show()
    }


    private fun checkBiometricCapability() {
      if(!BiometricUtils.isDeviceReady(this)){
          Toast.makeText(this, "Biometria no disponible en tu movil" , Toast.LENGTH_SHORT).show()
          finish()
      }else{
          showBiometricPrompt()
      }
    }

    private fun showBiometricPrompt() {
        BiometricUtils.showPrompt(
            activity = this,
            callback = this
        )
    }

    private fun saveUserData() {
        val shrpre : SharedPreferences = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor = shrpre.edit()
        editor.putString(NAME , etName.text.toString())
        editor.putString(MAIL , etEmail.text.toString())
        editor.putString(PHONE, etPhone.text.toString())
        editor.apply()
    }

    private fun fillUserData() {

        val getter:  SharedPreferences = getSharedPreferences(USER_DATA , Context.MODE_PRIVATE)
        etName.setText(getter.getString(NAME , " "))
        etEmail.setText(getter.getString(MAIL , " "))
        etPhone.setText(getter.getString(PHONE , " "))

    }



}

