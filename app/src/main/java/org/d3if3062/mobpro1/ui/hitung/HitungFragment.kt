package org.d3if3062.mobpro1.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if3062.mobpro1.R
import org.d3if3062.mobpro1.databinding.FragmentHitungBinding
import org.d3if3062.mobpro1.db.CalorieDao
import org.d3if3062.mobpro1.db.CalorieDb
import org.d3if3062.mobpro1.model.HasilCalorie
import org.d3if3062.mobpro1.model.KategoriCalorie

class HitungFragment: Fragment() {
    private lateinit var binding: FragmentHitungBinding
    private lateinit var calorieDao: CalorieDao


    private val viewModel: HitungViewModel by lazy {
        val db = CalorieDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCalculate.setOnClickListener{calculateCalories()}
        binding.shareButton.setOnClickListener{shareData()}
        binding.saranButton.setOnClickListener{viewModel.mulaiNavigasi()}

        viewModel.getHasilCalorie().observe(requireActivity(), {showResult(it)})
        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            findNavController().navigate(HitungFragmentDirections.actionHitungFragmentToSaranFragment(it))
            viewModel.selesaiNavigasi()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    (R.id.action_hitungFragment_to_historiFragment)
                )
                return true
            }

            R.id.menu_about -> {

                findNavController().navigate(
                    (R.id.action_hitungFragment_to_aboutFragment)
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun calculateCalories() {
        val weight = binding.editTextWeight.text.toString()
        if (TextUtils.isEmpty(weight)){
            Toast.makeText(context, R.string.berat_validasi, Toast.LENGTH_LONG).show()
            return
        }
        val height = binding.editTextHeight.text.toString()
        if (TextUtils.isEmpty(height)){
            Toast.makeText(context, R.string.tinggi_validasi, Toast.LENGTH_LONG).show()
            return
        }
        val age = binding.editTextAge.text.toString()
        if (TextUtils.isEmpty(age)){
            Toast.makeText(context, R.string.umur_validasi, Toast.LENGTH_LONG).show()
            return
        }

        val selectedId = binding.radioGroupGender.checkedRadioButtonId
        if (selectedId == -1){
            Toast.makeText(context, R.string.gender_validasi, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.calculateCalories(
            weight.toDouble(),
            height.toDouble(),
            age.toInt(),
            selectedId == R.id.radioButtonMale
        )

    }

    private fun shareData() {
        val selectedId = binding.radioGroupGender.checkedRadioButtonId
        val gender = if (selectedId == R.id.radioButtonMale)
            getString(R.string.txtMale)
        else
            getString(R.string.txtFemale)

        val message = getString(
            R.string.bagikan_tamplate,
            binding.editTextWeight.text,
            binding.editTextHeight.text,
            gender,
            binding.textViewResult.text,
            binding.kategoriTextView.text
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }

    }

    private fun showResult(result: HasilCalorie?){
        if (result == null) return

        binding.textViewResult.text = getString(R.string.kalori_x, result.hasil)
        binding.kategoriTextView.text = getString(R.string.kategori_x,
            getKategoriLabel(result.kategori))
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun getKategoriLabel (kategori: KategoriCalorie): String {
        val stringRes = when (kategori) {
            KategoriCalorie.KEKURANGAN -> R.string.kurang
            KategoriCalorie.CUKUP -> R.string.cukup
            KategoriCalorie.KELEBIHAN -> R.string.kelebihan
        }
        return getString(stringRes)
    }

}

