package org.d3if3062.mobpro1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if3062.mobpro1.R
import org.d3if3062.mobpro1.databinding.FragmentSaranBinding
import org.d3if3062.mobpro1.model.KategoriCalorie

class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }

    private fun updateUI(kategori: KategoriCalorie) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriCalorie.KEKURANGAN -> {
                actionBar?.title = getString(R.string.judul_kurang)
                binding.textView.text = getString(R.string.saran_kurang)
            }
            KategoriCalorie.CUKUP -> {
                actionBar?.title = getString(R.string.judul_cukup)
                binding.textView.text = getString(R.string.saran_cukup)
            }
            KategoriCalorie.KELEBIHAN -> {
                actionBar?.title = getString(R.string.judul_kelebihan)
                binding.textView.text = getString(R.string.saran_kelebihan)


            }
        }
    }

}
