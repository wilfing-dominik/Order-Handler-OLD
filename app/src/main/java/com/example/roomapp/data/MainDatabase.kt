package com.example.roomapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.model.InventoryItem

@Database(entities = [InventoryItem::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun InventoryItemDao(): InventoryItemDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}