package com.openwebinars.filmapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwebinars.filmapp.databinding.FragmentFavsBinding
import com.openwebinars.filmapp.viewmodel.FavsViewModel

class FavsFragment : Fragment() {

    private var _binding: FragmentFavsBinding? = null
    private val binding get() = _binding!!

    private val favsViewModel: FavsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favsViewModel.onViewCreated()
        favsViewModel.favsLiveData.observe(viewLifecycleOwner, { films ->
            with(binding.recyclerFavs) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = FavsAdapter(films)
            }
        })
        favsViewModel.isLoading.observe(viewLifecycleOwner,  { isLoading ->
            binding.pbLoading.isVisible = isLoading
        })
    }
}