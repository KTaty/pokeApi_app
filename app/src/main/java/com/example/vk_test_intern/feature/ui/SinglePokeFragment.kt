package com.example.vk_test_intern.feature.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vk_test_intern.feature.presentation.SinglePokeViewModel
import com.example.vk_test_intern.core.ViewModelProvider
import com.example.vk_test_intern.databinding.FrSinglePokemonBinding
import com.example.vk_test_intern.feature.presentation.SinglePokeAction
import com.example.vk_test_intern.feature.presentation.SinglePokeViewState
import com.example.vk_test_intern.utils.loadPokeForm
import kotlinx.coroutines.launch

class SinglePokeFragment : Fragment() {

    private var _binding: FrSinglePokemonBinding? = null
    private val binding get() = _binding!!
    private val args: SinglePokeFragmentArgs by navArgs()

    private val adapter: StatsListAdapter by lazy { StatsListAdapter()}

    var feature: SinglePokeViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        _binding = FrSinglePokemonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.statList.adapter = adapter
        binding.statList.layoutManager = LinearLayoutManager(view.context)

        feature = ViewModelProvider.obtainFeature {
            SinglePokeViewModel()
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                feature?.viewStateFlow?.collect {
                    renderState(it)
                }
            }
        }
        feature?.submitAction(SinglePokeAction.Init(args.pokeName?:""))

    }

    override fun onDestroy() {
        _binding = null
        feature?.let {
            if (activity?.isFinishing == true) {
                ViewModelProvider.destroyFeature(it::class)
            }
        }
        super.onDestroy()
    }

    private fun renderState(viewState: SinglePokeViewState) {
        when (viewState) {
            is SinglePokeViewState.Error -> {
                binding.error.isVisible = true
                binding.error.text = viewState.errorText
                binding.loader.isVisible = false
                binding.statList.isVisible = false
            }
            is SinglePokeViewState.StatsLoaded -> {
                binding.loader.isVisible = false
                binding.error.isVisible = false
                binding.statList.isVisible = true
                binding.pokemonName.text = viewState.name
                loadPokeForm(binding.pokemonForm,viewState.id)
                adapter.setStats(viewState.statsList)
            }
            SinglePokeViewState.Loading -> {
                binding.error.isVisible = false
                binding.loader.isVisible = true
                binding.statList.isVisible = false
            }
        }
    }
}