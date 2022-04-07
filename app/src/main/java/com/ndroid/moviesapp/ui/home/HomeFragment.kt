package com.ndroid.moviesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ndroid.moviesapp.databinding.FragmentHomeBinding
import com.ndroid.moviesapp.utils.Resource
import com.ndroid.moviesapp.utils.hide
import com.ndroid.moviesapp.utils.show
import com.ndroid.moviesapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
        setupObservers()
    }

    private fun setupListAdapter() {
        val viewModel = binding.viewModel
        if (viewModel != null) {
            binding.recyclerViewMovies.apply {
                adapter = moviesAdapter
            }
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
        moviesAdapter.setOnItemClickListener { movie ->
            Timber.w("HomeFragment: ${movie.title}")
            // val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movie.id)
            // findNavController().navigate(action)
        }
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    hideErrorMessage()
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    moviesAdapter.submitList(response.data.toList())
                }
                is Resource.Error -> {
                    hideProgressBar()
                    showToast("An error occured: ${response.exception.message}", Toast.LENGTH_LONG)
                    Timber.e(response.exception)
                    response.exception.message?.let { showErrorMessage(it) }
                }
            }
        }
    }


    private fun hideProgressBar() {
        binding.progressBar.hide()
    }

    private fun showProgressBar() {
        binding.progressBar.show()
    }

    private fun hideErrorMessage() {
        binding.itemErrorMessage.hide()
    }

    private fun showErrorMessage(message: String) {
        binding.itemErrorMessage.show()
        binding.tvErrorMessage.text = message
    }

}