package com.example.roomapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "orders_table")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val food_id: Int,
    val table_id: Int
): Parcelable