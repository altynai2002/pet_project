package com.pro.petproject.ui.addPost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pro.petproject.databinding.FragmentAddpostBinding
import com.pro.petproject.ui.Navigate
import com.pro.petproject.ui.PostListFragment
import com.pro.petproject.ui.base.BaseFragment
import com.pro.petproject.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostFragment: BaseFragment<AddPostViewModel>(AddPostViewModel::class.java) {
    private lateinit var listener : Navigate
    private var _binding: FragmentAddpostBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Navigate
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentAddpostBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddpostBinding.bind(view)

        binding.apply {
            postBtn.setOnClickListener {
                viewModel.addPost(editTitle.text.toString(), editContent.text.toString())
                listener.openFragment(PostListFragment())
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearEvents()
    }
}