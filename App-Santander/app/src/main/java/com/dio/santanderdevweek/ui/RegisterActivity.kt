package com.dio.santanderdevweek.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dio.santanderdevweek.R
import com.dio.santanderdevweek.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun registerUser() {
        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()
        val agency = binding.editTextAgency.text.toString()
        val account = binding.editTextAccount.text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || agency.isEmpty() || account.isEmpty()) {
            Snackbar.make(binding.layoutRegister, "Preencha todos os campos!", Snackbar.LENGTH_SHORT).show()
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(binding.layoutRegister, "Cadastro realizado com sucesso!", Snackbar.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                var error = it
                when {
                    error is FirebaseAuthWeakPasswordException -> Snackbar.make(binding.layoutRegister, "Digite uma senha com no mínimo 6 caracteres", Snackbar.LENGTH_SHORT).show()
                    error is FirebaseAuthUserCollisionException -> Snackbar.make(binding.layoutRegister, "Dados já cadastrados", Snackbar.LENGTH_SHORT).show()
                    error is FirebaseNetworkException -> Snackbar.make(binding.layoutRegister, "Sem conexão com á internet", Snackbar.LENGTH_SHORT).show()
                    else -> Snackbar.make(binding.layoutRegister, "Erro ao cadastrar usuário", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun changeScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}