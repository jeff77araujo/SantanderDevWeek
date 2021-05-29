package com.dio.santanderdevweek.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dio.santanderdevweek.data.Account
import com.dio.santanderdevweek.data.local.FakeData

class MainViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<Account> = MutableLiveData()

    fun searchClient() : LiveData<Account> {
        mutableLiveData.value = FakeData().getLocalData()
        return mutableLiveData
    }
}