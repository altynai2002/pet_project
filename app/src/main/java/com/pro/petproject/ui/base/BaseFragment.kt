package com.pro.petproject.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

open class BaseFragment<viewModel: BaseViewModel>(
    private val vmClass: Class<viewModel>,
): Fragment() {
    protected lateinit var viewModel: viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[vmClass]
    }

}