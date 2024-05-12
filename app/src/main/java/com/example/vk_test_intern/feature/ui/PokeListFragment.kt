package com.example.vk_test_intern.feature.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.vk_test_intern.R
import com.example.vk_test_intern.feature.model.Pokemon
import com.example.vk_test_intern.databinding.FrPokeListBinding
import com.example.vk_test_intern.feature.presentation.PokemonListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PokeListFragment : Fragment() {

   private var _binding: FrPokeListBinding? = null
   private val binding get() = _binding!!


    private val adapter: PokePagingAdapter by lazy { PokePagingAdapter{ pokemon ->
        navigateToSinglePokeFragment(pokemon)
    } }

    private val viewModel: PokemonListViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FrPokeListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recycler.adapter = adapter.withLoadStateHeaderAndFooter(
            LoadStateAdapter { adapter.retry() },
            LoadStateAdapter { adapter.retry() }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPokemons().collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        adapter.addLoadStateListener {state->
            binding.recycler.isVisible = state.refresh != LoadState.Loading
            binding.loader.isVisible = state.refresh == LoadState.Loading
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun navigateToSinglePokeFragment(pokemon: Pokemon){
        val action = PokeListFragmentDirections.actionPokeListFragmentToSinglePokeFragment(pokemon.name)
        findNavController().navigate(action)
    }
}

