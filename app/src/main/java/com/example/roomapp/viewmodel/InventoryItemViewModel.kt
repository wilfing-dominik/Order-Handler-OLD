package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.MainDatabase
import com.example.roomapp.model.InventoryItem
import com.example.roomapp.repository.InventoryItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventoryItemViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<InventoryItem>>
    private val repository: InventoryItemRepository

    init {
        val inventoryItemDao = MainDatabase.getDatabase(
            application
        ).InventoryItemDao()
        repository = InventoryItemRepository(inventoryItemDao)
        readAllData = repository.readAllData
    }

    fun addInventoryItem(inventoryItem: InventoryItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addInventoryItem(inventoryItem)
        }
    }

    fun updateInventoryItem(inventoryItem: InventoryItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInventoryItem(inventoryItem)
        }
    }

    fun deleteInventoryItem(inventoryItem: InventoryItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteInventoryItem(inventoryItem)
        }
    }

    fun deleteAllInventoryItem(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllInventoryItem()
        }
    }

}