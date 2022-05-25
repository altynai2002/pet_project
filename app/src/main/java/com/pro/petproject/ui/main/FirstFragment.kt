package com.pro.petproject.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pro.petproject.R
import com.pro.petproject.databinding.FragmentFirstBinding
import com.pro.petproject.ui.Navigate
import com.pro.petproject.ui.base.BaseFragment
import com.pro.petproject.ui.login.LoginFragment
import com.pro.petproject.ui.registration.RegistrationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment: BaseFragment<MainViewModel>(MainViewModel::class.java) {
    private lateinit var listener : Navigate
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Navigate
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        view.visibility = View.GONE

        //  переход на login/registration fragment
        binding.apply {
            regBtn.setOnClickListener {
                listener.openFragment(RegistrationFragment())
            }
            loginBtn.setOnClickListener {
                listener.openFragment(LoginFragment())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearEvents()
    }


//    override fun onClick(index: Int) {
//        viewModel.getPostByIndex(index)?.let {
//            listener.openFragment(PostFragment.newInstance(it.id!!))
//        }
//    }
}