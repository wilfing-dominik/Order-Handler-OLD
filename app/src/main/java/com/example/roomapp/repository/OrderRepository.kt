package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.InventoryItemDao
import com.example.roomapp.data.OrderDao
import com.example.roomapp.model.InventoryItem
import com.example.roomapp.model.Order

class OrderRepository(private val orderDao: OrderDao) {

    val readAllOrder: LiveData<List<Order>> = orderDao.readAllOrder()

    suspend fun addOrder(order: Order){
        orderDao.addOrder(order)
    }

    suspend fun updateOrder(order: Order){
        orderDao.updateOrder(order)
    }

    suspend fun deleteOrder(order: Order){
        orderDao.deleteOrder(order)
    }

    suspend fun deleteAllOrder(){
        orderDao.deleteAllOrder()
    }

}