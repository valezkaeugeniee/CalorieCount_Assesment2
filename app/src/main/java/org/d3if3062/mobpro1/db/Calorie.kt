package org.d3if3062.mobpro1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calorie_table")
data class Calorie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weight: Double,
    val height: Double,
    val age: Int,
    val isMale: Boolean
)
