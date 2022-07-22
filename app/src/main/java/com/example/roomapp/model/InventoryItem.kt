package com.example.roomapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "inventory_table")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price_huf: Int,
    val price_eur: Int
): Parcelable