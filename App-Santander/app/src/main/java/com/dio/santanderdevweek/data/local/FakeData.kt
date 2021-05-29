package com.dio.santanderdevweek.data.local

import com.dio.santanderdevweek.data.Account
import com.dio.santanderdevweek.data.Card
import com.dio.santanderdevweek.data.Client

class FakeData {
    fun getLocalData(): Account {
        val client = Client("Jefferson")
        val numberCard = Card("5432")
        return Account(
            "01234-5",
            "6543",
            "2.450,80",
            "4.450,80",
            client,
            numberCard
        )
    }
}