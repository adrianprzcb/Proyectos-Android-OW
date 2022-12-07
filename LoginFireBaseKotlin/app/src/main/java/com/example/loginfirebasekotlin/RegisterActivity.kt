package com.example.loginfirebasekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class RegisterActivity : AppCompatActivity() {

    private lateinit var regMail: EditText
    private lateinit var regPassword: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var regButton: Button
    private lateinit var goLogin: Button

       private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

          auth = Firebase.auth


        regMail = findViewById(R.id.regMail)
        regPassword = findViewById(R.id.regPassword)
        repeatPassword = findViewById(R.id.repeatPassword)
        regButton = findViewById(R.id.regButton)
        goLogin = findViewById(R.id.goLogin)


        regButton.setOnClickListener {
            var email = regMail.text.toString()
            var pass = regPassword.text.toString()
            var pass2 = repeatPassword.text.toString()

            if(pass.equals(pass2) && checkNotEmpty(email , pass)){
                register(email , pass)
            }
        }

        goLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun checkNotEmpty(email: String, pass: String): Boolean {
            return email.isNotEmpty() && pass.isNotEmpty()
    }

    private fun register(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) {
                task -> if (task.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else {
                    Toast.makeText(applicationContext , "Register Failed" , Toast.LENGTH_LONG).show()
            }
            }
            }
    }
