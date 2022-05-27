package com.example.testprog.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.testprog.App
import com.example.testprog.R
import com.example.testprog.data.ZabbixAPIRepositoryImp
import com.example.testprog.databinding.FragmentAuthBinding
import com.example.testprog.ui.main.MainFragment

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private val viewModel: AuthViewModel by viewModels { AuthViewModelFactory(ZabbixAPIRepositoryImp()) }
    private lateinit var binding: FragmentAuthBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)
        binding.logonButton.setOnClickListener {
            App.instance.setAuthParam(
                binding.usernameEditText.text.toString(),
                binding.passwdEditText.text.toString()
            )
            viewModel.loadingAuthZabbix(App.instance.getAutParam())
        }
        initLifecycle()


    }

    fun initLifecycle() {
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.loading.collect {
                binding.authFragmentProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.error.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.authZab.collect { authZab ->
                authZab?.let {
                    if (it.error == null) {
                        binding.passwdTextlayout.error = null
                        Toast.makeText(requireContext(), "Авторизация успешна", Toast.LENGTH_LONG)
                            .show()
                        App.instance.setAuthKey(it.authKey)
                        val manager = activity?.supportFragmentManager
                        manager?.beginTransaction()
                            ?.replace(R.id.fragment_container_view, MainFragment())
                            ?.commit()
                    } else {
                        binding.passwdTextlayout.error = it.error.data
                        Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}