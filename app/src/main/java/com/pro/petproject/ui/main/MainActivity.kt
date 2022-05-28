package com.pro.petproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pro.petproject.R
import com.pro.petproject.databinding.ActivityMainBinding
import com.pro.petproject.ui.*
import com.pro.petproject.ui.addPost.AddPostFragment
import com.pro.petproject.ui.profile.ProfileFragment
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
            openFragment(FirstFragment())
        }

        supportActionBar?.hide()
        bottomNavView = binding.bottomNav

        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    openFragment(PostListFragment(), true)
                }
                R.id.message -> {
                    openFragment(AddPostFragment(), true)
                }
                R.id.settings -> {
                    openFragment(ProfileFragment(), true)
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