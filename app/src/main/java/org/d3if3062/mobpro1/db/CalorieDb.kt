package org.d3if3062.mobpro1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Calorie::class], version = 1, exportSchema = false)
abstract class CalorieDb :RoomDatabase(){
    abstract val dao:CalorieDao

    companion object{
        @Volatile
        private var INSTANCE:CalorieDb? = null

        fun getInstance(context: Context): CalorieDb{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CalorieDb::class.java,
                        "calorie.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
            }
                return instance
            }
        }
    }
}