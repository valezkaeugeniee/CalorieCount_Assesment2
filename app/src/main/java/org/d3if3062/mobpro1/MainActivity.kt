package org.d3if3062.mobpro1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if3062.mobpro1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener { Reset() }
        binding.btnBagi.setOnClickListener{
            val input1 = binding.bilangan1.text.toString().trim()
            val input2 = binding.bilangan2.text.toString().trim()
            var hasil = 0

            when {
                TextUtils.isEmpty(input1) -> {
                    Toast.makeText(this, "Bilangan Pertama Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan1.requestFocus()
                }
                TextUtils.isEmpty(input2) -> {
                    Toast.makeText(this, "Bilangan Kedua Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan1.requestFocus()
                }
                else -> {
                    hasil = input1.toInt() / input2.toInt()
                    binding.total.text = hasil.toString()
                }
            }
        }
        binding.btnKali.setOnClickListener{
            val input1 = binding.bilangan1.text.toString().trim()
            val input2 = binding.bilangan2.text.toString().trim()
            var hasil = 0

            when {
                TextUtils.isEmpty(input1) -> {
                    Toast.makeText(this, "Bilangan Pertama Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan1.requestFocus()
                }
                TextUtils.isEmpty(input2) -> {
                    Toast.makeText(this, "Bilangan Kedua Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan2.requestFocus()
                }
                else -> {
                    hasil = input1.toInt() * input2.toInt()
                    binding.total.text = hasil.toString()
                }
            }
        }
        binding.btnKurang.setOnClickListener{
            val input1 = binding.bilangan1.text.toString().trim()
            val input2 = binding.bilangan2.text.toString().trim()
            var hasil = 0

            when {
                TextUtils.isEmpty(input1) -> {
                    Toast.makeText(this, "Bilangan Pertama Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan1.requestFocus()
                }
                TextUtils.isEmpty(input2) -> {
                    Toast.makeText(this, "Bilangan Kedua Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan2.requestFocus()
                }
                else -> {
                    hasil = input1.toInt() - input2.toInt()
                    binding.total.text = hasil.toString()
                }
            }
        }
        binding.btnTambah.setOnClickListener{
            val input1 = binding.bilangan1.text.toString().trim()
            val input2 = binding.bilangan2.text.toString().trim()
            var hasil = 0

            when {
                TextUtils.isEmpty(input1) -> {
                    Toast.makeText(this, "Bilangan Pertama Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan1.requestFocus()
                }
                TextUtils.isEmpty(input2) -> {
                    Toast.makeText(this, "Bilangan Kedua Belum Ada!", Toast.LENGTH_SHORT).show()
                    binding.bilangan2.requestFocus()
                }
                else -> {
                    hasil = input1.toInt() + input2.toInt()
                    binding.total.text = hasil.toString()
                }
            }
        }
    }





    private fun Reset() {
        binding.bilangan1.text?.clear()
        binding.bilangan2.text?.clear()
        binding.total.setText("")

    }

}


