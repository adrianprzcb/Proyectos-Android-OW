package com.openwebinars.filmapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.openwebinars.filmapp.databinding.FragmentNewFilmsBinding
import com.openwebinars.filmapp.view.DetailFragment.Companion.EXTRA
import com.openwebinars.filmapp.viewmodel.NewsViewModel

class NewFilmsFragment : Fragment() {

    private var _binding: FragmentNewFilmsBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel : NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.getNews()
        newsViewModel.newsLiveData.observe(viewLifecycleOwner, { films ->
            with(binding.recyclerNews) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = FilmsAdapter(films) {
                    val intentDetail = Intent(context, DetailActivity::class.java)
                    intentDetail.putExtra(EXTRA, it)
                    startActivity(intentDetail)
                }
            }
        })
        newsViewModel.isLoading.observe(viewLifecycleOwner,  { isLoading ->
            binding.pbLoading.isVisible = isLoading
        })
    }
}