package org.d3if3062.mobpro1.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3062.mobpro1.db.CalorieDao

class HistoriViewModel(private val db: CalorieDao) : ViewModel() {
    val data = db.getAllCalories()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}