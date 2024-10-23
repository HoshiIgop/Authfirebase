package com.example.userfirebaseauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userfirebaseauth.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SIgnUp : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signinbtn.setOnClickListener {
            signUp()
        }
        binding.loginText.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun signUp() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val confirm = binding.confirm.text.toString()

        if(email.isEmpty() && password.isEmpty() && confirm.isEmpty()){
            Toast.makeText(this, "Please fill in empty fields", Toast.LENGTH_SHORT).show()
        }
        if(confirm != password){
            Toast.makeText(this, "Incorrect password input", Toast.LENGTH_SHORT).show()
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }
}