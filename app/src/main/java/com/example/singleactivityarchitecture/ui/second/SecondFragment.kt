package com.example.singleactivityarchitecture.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.singleactivityarchitecture.R
import com.example.singleactivityarchitecture.databinding.FirstFragmentBinding
import com.example.singleactivityarchitecture.databinding.SecondFragmentBinding
import com.example.singleactivityarchitecture.ui.firstFragment.MovieAdapter
import com.example.singleactivityarchitecture.ui.firstFragment.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment:Fragment() {
    private var _binding: SecondFragmentBinding? = null
    private val binding get() = _binding!!

    private val myViewModel: SecondFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SecondFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getLong("posterId")
        val bg = requireActivity().findViewById<ImageView>(R.id.dynamicBackground)
        lifecycleScope.launch {
            myViewModel.getSelectiveItem(id).collect{
                Glide.with(requireContext())
                    .load(it.poster)
                    .error(R.drawable.ic_launcher_background)
                    .into(bg)
                Glide.with(requireContext())
                    .load(it.poster)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.imageView)
                binding.description.text = it.description
            }
        }
    }
}