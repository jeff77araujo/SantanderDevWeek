package com.dio.santanderdevweek.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dio.santanderdevweek.R
import com.dio.santanderdevweek.data.Account
import com.dio.santanderdevweek.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        searchClient()

        //throw RuntimeException("Test Crash")
    }

    private fun searchClient() {
        mainViewModel.searchClient().observe(this, Observer {
            Log.d("Agencia", "onCreate: ${it.agency}")
            bindOnView(it)
        })
    }

    private fun bindOnView(details: Account) {
        with(binding) {
            textAgency.text = details.agency
            textCurrentAccount.text = details.number
            textUser.text = details.client.name
            textValueMoney.text = details.saldo
            textValueTotal.text = details.limit
            numberFinal.text = details.card.numberCard
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_1 -> {
                true
            }
            R.id.item_2 -> {
                FirebaseAuth.getInstance().signOut()
                backLogin()
                finish()
                true
            }
            else -> false
        }
    }

    private fun backLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}