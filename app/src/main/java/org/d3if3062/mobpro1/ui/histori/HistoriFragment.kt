package org.d3if3062.mobpro1.ui.histori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if3062.mobpro1.databinding.FragmentHistoriBinding

class HistoriFragment : Fragment() {
    private lateinit var binding: FragmentHistoriBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriBinding.inflate(layoutInflater,
            container, false)
        return binding.root
    }
}
