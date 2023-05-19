package org.d3if3062.mobpro1.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3062.mobpro1.R
import org.d3if3062.mobpro1.databinding.ItemHistoriBinding
import org.d3if3062.mobpro1.db.Calorie
import org.d3if3062.mobpro1.model.CalorieCalculator
import org.d3if3062.mobpro1.model.KategoriCalorie
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<Calorie, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<Calorie>() {
                override fun areItemsTheSame(
                    oldData: Calorie, newData: Calorie
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: Calorie, newData: Calorie
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: Calorie) = with(binding) {
            val hasilCalorie = item.CalorieCalculator()
            kategoriTextView.text = hasilCalorie.kategori.toString().substring(0, 1)
            val colorRes = when (hasilCalorie.kategori) {
                KategoriCalorie.KEKURANGAN -> R.color.kurang
                KategoriCalorie.CUKUP -> R.color.cukup
                else -> R.color.kelebihan
            }
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            calorieTextView.text = root.context.getString(
                R.string.hasil_x,
                hasilCalorie.hasil, hasilCalorie.kategori.toString()
            )
            val gender = root.context.getString(
                if (item.isMale) R.string.radio_button_male else R.string.radio_button_female
            )
            dataTextView.text = root.context.getString(
                R.string.data_x,
                item.weight, item.height, gender
            )

        }
    }
}