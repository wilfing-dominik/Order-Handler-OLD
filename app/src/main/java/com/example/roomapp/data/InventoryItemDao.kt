package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.InventoryItem

@Dao
interface InventoryItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInventoryItem(InventoryItem: InventoryItem)

    @Update
    suspend fun updateInventoryItem(InventoryItem: InventoryItem)

    @Delete
    suspend fun deleteInventoryItem(InventoryItem: InventoryItem)

    @Query("DELETE FROM inventory_table")
    suspend fun deleteAllInventoryItem()

    @Query("SELECT * FROM inventory_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<InventoryItem>>

}