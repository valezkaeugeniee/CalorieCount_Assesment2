package org.d3if3062.mobpro1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3062.mobpro1.db.Calorie
import org.d3if3062.mobpro1.db.CalorieDao
import org.d3if3062.mobpro1.model.CalorieCalculator
import org.d3if3062.mobpro1.model.HasilCalorie
import org.d3if3062.mobpro1.model.KategoriCalorie

class HitungViewModel(private val db: CalorieDao) : ViewModel() {
    private val hasilCalorie = MutableLiveData<HasilCalorie?>()
    private val navigasi = MutableLiveData<KategoriCalorie?>()

    fun calculateCalories(weight: Double, height: Double, age: Int, isMale: Boolean) {
        val dataCalorie = Calorie(
            weight = weight,
            height = height,
            age = age,
            isMale = isMale
        )
        hasilCalorie.value = dataCalorie.CalorieCalculator()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataCalorie)
            }
        }
    }

    fun getHasilCalorie(): LiveData<HasilCalorie?> = hasilCalorie
    fun mulaiNavigasi() {
        navigasi.value = hasilCalorie.value?.kategori
    }
    fun selesaiNavigasi() {
        navigasi.value = null
    }
    fun getNavigasi() : LiveData<KategoriCalorie?> = navigasi
}
