package com.dio.santanderdevweek.data

data class Account(
    val number: String,
    val agency: String,
    val saldo: String,
    val limit: String,
    val client: Client,
    val card: Card
)