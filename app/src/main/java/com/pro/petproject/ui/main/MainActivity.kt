package com.pro.petproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pro.petproject.R
import com.pro.petproject.databinding.ActivityMainBinding
import com.pro.petproject.ui.*
import com.pro.petproject.ui.profile.ProfileFragment
import com.pro.petproject.ui.registration.RegistrationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), Navigate {
    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNavView : BottomNavigationView
//    protected lateinit var vm: viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
//            binding.bottomNav.visibility = View.GONE
            openFragment(FirstFragment(), false)
        }

        supportActionBar?.hide()
        bottomNavView = binding.bottomNav

        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    openFragment(PostListFragment(), false)
                }
                R.id.message -> {
                    openFragment(AddPostFragment(), false)
                }
                R.id.settings -> {
                    openFragment(ProfileFragment(), false)
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