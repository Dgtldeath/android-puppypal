package com.adamgumm.puppypal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adamgumm.puppypal.databinding.FragmentBrowseBinding

class BrowseFragment : Fragment() {
    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PuppyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.puppyList.layoutManager = LinearLayoutManager(context)

        viewModel.puppies.observe(viewLifecycleOwner) { puppies ->
            Log.d("BrowseFragment", "Puppies updated: ${puppies.size}, First distance: ${puppies.firstOrNull()?.distance}")
            binding.puppyList.adapter = PuppyAdapter(puppies)
        }

        // Fetch and sort with Columbus hardcoded
        viewModel.fetchPuppies(requireContext())

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val filter = s.toString().lowercase()
                viewModel.puppies.value?.let {
                    binding.puppyList.adapter = PuppyAdapter(it.filter { puppy ->
                        puppy.breed.lowercase().contains(filter)
                    })
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
