package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.InventoryItemDao
import com.example.roomapp.model.InventoryItem

class InventoryItemRepository(private val inventoryItemDao: InventoryItemDao) {

    val readAllData: LiveData<List<InventoryItem>> = inventoryItemDao.readAllData()

    suspend fun addInventoryItem(inventoryItem: InventoryItem){
        inventoryItemDao.addInventoryItem(inventoryItem)
    }

    suspend fun updateInventoryItem(inventoryItem: InventoryItem){
        inventoryItemDao.updateInventoryItem(inventoryItem)
    }

    suspend fun deleteInventoryItem(inventoryItem: InventoryItem){
        inventoryItemDao.deleteInventoryItem(inventoryItem)
    }

    suspend fun deleteAllInventoryItem(){
        inventoryItemDao.deleteAllInventoryItem()
    }

}