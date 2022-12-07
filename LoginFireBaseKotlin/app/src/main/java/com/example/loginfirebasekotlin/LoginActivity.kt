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

class LoginActivity : AppCompatActivity() {

    private lateinit var loginMail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        loginMail = findViewById(R.id.loginMail)
        loginPassword = findViewById(R.id.loginPassword)
         loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.goRegister)

        registerButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))

        }

       loginButton.setOnClickListener {
           var email = loginMail.text.toString()
           var pass = loginPassword.text.toString()
           if(checkEmpty(email , pass)){
                login(email, pass)
           }
       }

    }

    private fun checkEmpty(email: String, pass: String): Boolean {

        return email.isNotEmpty() && pass.isNotEmpty()
    }

    private fun login(email: String , pass: String){
        auth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(this){
                task -> if(task.isSuccessful){
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(applicationContext , "Register Failed" , Toast.LENGTH_LONG).show()
        }
        }
    }

}

