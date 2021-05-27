package com.example.phonebook

import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhoneBookViewModel : ViewModel() {

    val contactList = mutableListOf<ContactData>()
    val contact = MutableLiveData<MutableList<ContactData>>()

    fun addToList(){
    }

    private fun insert(contactData: ContactData) {
    }
}