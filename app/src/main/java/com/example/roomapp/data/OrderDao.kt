package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.InventoryItem
import com.example.roomapp.model.Order

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrder(Order: Order)

    @Update
    suspend fun updateOrder(Order: Order)

    @Delete
    suspend fun deleteOrder(Order: Order)

    @Query("DELETE FROM orders_table")
    suspend fun deleteAllOrder()

    @Query("SELECT * FROM orders_table ORDER BY id ASC")
    fun readAllOrder(): LiveData<List<Order>>

}