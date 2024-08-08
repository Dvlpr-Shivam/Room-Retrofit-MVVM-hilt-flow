package com.example.singleactivityarchitecture.ui.firstFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.singleactivityarchitecture.Network.DataResult
import com.example.singleactivityarchitecture.R
import com.example.singleactivityarchitecture.dataBase.table.Poster
import com.example.singleactivityarchitecture.databinding.FirstFragmentBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FirstFragment  : Fragment() ,MovieAdapter.OnAdapterClick{

    private var _binding: FirstFragmentBinding? = null
    private val binding get() = _binding!!

    private val myViewModel: MyViewModel by viewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FirstFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter(this)
        binding.itemView.layoutManager= GridLayoutManager(requireContext(),2)
        binding.itemView.adapter= adapter
        lifecycleScope.launch {
            /*myViewModel.allData.collect { data ->
                // Update UI
            }*/
            myViewModel.data.collect{ result->
                when (result) {
                    is DataResult.Success -> {
                        Timber.tag("PosterdataList==>").d("${result.data}")
                        adapter.updateView(result.data)
                        // Update UI with the success result
                        // e.g., textView.text = result.data.someField
                    }
                    is DataResult.Error -> {
                        Timber.tag("PosterdataList==>").d(result.exception)
                        // Handle the error
                        // e.g., show error message
                    }
                    else -> { Timber.tag("PosterdataList==>").d("$result")}
                }
            }
        }

       /* binding.submit.setOnClickListener {
//            Snackbar.make(requireView(),"",10000).show()
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClick(poster: Poster) {
        val bundle = bundleOf("posterId" to poster.id)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)
    }
}