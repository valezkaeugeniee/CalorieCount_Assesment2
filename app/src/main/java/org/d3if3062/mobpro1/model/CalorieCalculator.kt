package org.d3if3062.mobpro1.model

import org.d3if3062.mobpro1.db.Calorie

fun Calorie.CalorieCalculator(): HasilCalorie {

    val hasilCalorie = if (isMale){
        66 + (13.75 * weight) + (5 * height) - (6.75 * age)
    }else{
        655 + (9.56 * weight) + (1.85 * height) - (4.68 * age)
    }

    val kategori = if (isMale){
        when{
            hasilCalorie < 2000 -> KategoriCalorie.KEKURANGAN
            hasilCalorie > 2500 -> KategoriCalorie.KELEBIHAN
            else -> KategoriCalorie.CUKUP
        }
    }else{
        when{
            hasilCalorie < 2000 -> KategoriCalorie.KEKURANGAN
            hasilCalorie > 2500 -> KategoriCalorie.KELEBIHAN
            else -> KategoriCalorie.CUKUP
        }
    }

    return HasilCalorie(hasilCalorie.toFloat(), kategori)
}

