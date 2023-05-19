package org.d3if3062.mobpro1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalorieDao {
    @Insert
    fun insert(calorie: Calorie)

    @Query("SELECT * FROM calorie_table")
     fun getAllCalories():LiveData <List<Calorie>>

     @Query("DELETE FROM calorie_table")
     fun clearData()
}
