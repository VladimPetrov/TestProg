package com.example.testprog.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.testprog.R
import com.example.testprog.data.ZabbixAPIRepositoryImp
import com.example.testprog.data.ZabbixLocalRepositoryImpl
import com.example.testprog.databinding.FragmentMainBinding

class MainFragment:Fragment(R.layout.fragment_main) {
    private val viewModel:ProblemViewModel by viewModels {
        ProblemViewModel.ProblemViewModelFactory(
            ZabbixAPIRepositoryImp()
            //ZabbixLocalRepositoryImpl()
        )
    }
    private lateinit var binding : FragmentMainBinding
    private val adapter = ProblemRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.fragmentMainRecyclerView.adapter = adapter
        initLifecycle()
        if (savedInstanceState == null) {
            viewModel.loadingProblemZabbix()
        }
        initFAB()
    }

    private fun initFAB() {
        binding.fragmentFab.setOnClickListener {
            viewModel.loadingProblemZabbix()
        }
    }

    fun initLifecycle() {
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.loading.collect {
                binding.fragmentMainProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.error.collect {
                Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
            }
        }
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.problemZab.collect { problemZab ->
                problemZab?.let { adapter.setData(it.result) }
            }
        }
    }
}