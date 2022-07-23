package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.MainDatabase
import com.example.roomapp.model.InventoryItem
import com.example.roomapp.model.Order
import com.example.roomapp.repository.InventoryItemRepository
import com.example.roomapp.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel(application: Application): AndroidViewModel(application) {

    val readAllOrder: LiveData<List<Order>>
    private val repository: OrderRepository

    init {
        val orderDao = MainDatabase.getDatabase(
            application
        ).OrderDao()
        repository = OrderRepository(orderDao)
        readAllOrder = repository.readAllOrder
    }

    fun addOrder(order: Order){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOrder(order)
        }
    }

    fun updateOrder(order: Order){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOrder(order)
        }
    }

    fun deleteOrder(order: Order){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOrder(order)
        }
    }

    fun deleteAllOrder(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllOrder()
        }
    }

}