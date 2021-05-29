package com.dio.santanderdevweek.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.dio.santanderdevweek.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verifyUserLogin()

        binding.buttonEnter.setOnClickListener {
            authentication()
        }

        binding.buttonRegister.setOnClickListener {
            openRegister()
        }
    }

    private fun authentication() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val agency = binding.editAgency.text.toString()
        val account = binding.editTextAccount.text.toString()

        if (email.isEmpty() || password.isEmpty() || agency.isEmpty() || account.isEmpty()) {
            Snackbar.make(binding.layoutLogin, "Preencha todos os campos!", Snackbar.LENGTH_SHORT).show()
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    openMainActivity()
                }
            }.addOnFailureListener {
                var error = it

                when {
                    error is FirebaseAuthInvalidCredentialsException -> Snackbar.make(binding.layoutLogin, "Dados incorretos", Snackbar.LENGTH_SHORT).show()
                    error is FirebaseNetworkException -> Snackbar.make(binding.layoutLogin, "Sem conexão com á internet", Snackbar.LENGTH_SHORT).show()
                    else -> Snackbar.make(binding.layoutLogin, "Erro ao logar usuário", Snackbar.LENGTH_SHORT).show()
                }
            }

        }
    }


    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun openRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }


    private fun verifyUserLogin() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            changeMain()
        }
    }

    private fun changeMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}