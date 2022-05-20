package com.pro.petproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pro.petproject.R
import com.pro.petproject.databinding.ActivityMainBinding
import com.pro.petproject.ui.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), Navigate {
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNavView : BottomNavigationView
//    protected lateinit var vm: viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        vm = ViewModelProvider(this)[vmClass]
        setContentView(binding.root)

        if(savedInstanceState == null){
            openFragment(ProfileFragment(), false)
        }

        supportActionBar?.hide()
        bottomNavView = binding.bottomNav

        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    openFragment(ProfileFragment(), false)
                }
                R.id.message -> {
                    openFragment(RegistrationFragment(), false)
                }
                R.id.settings -> {
                    openFragment(PostListFragment(), false)
                }
            }
            true
        }
    }


    override fun openFragment(fragment: Fragment, addToBackStack: Boolean?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frContainer, fragment).apply {
                if (addToBackStack == true) {
                    addToBackStack("")
                }
            }
            .commit()
    }
}