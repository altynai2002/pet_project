package com.pro.petproject.ui.addComment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pro.petproject.databinding.FragmentAddcommentBinding
import com.pro.petproject.ui.Navigate
import com.pro.petproject.ui.PostListFragment
import com.pro.petproject.ui.base.BaseFragment
import com.pro.petproject.ui.comment.CommentListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddCommentFragment: BaseFragment<AddCommentViewModel>(AddCommentViewModel::class.java) {
    private lateinit var listener: Navigate
    private var _binding: FragmentAddcommentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Navigate
        } catch (e: Exception) {
            print("Activity must implement FragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddcommentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddcommentBinding.bind(view)

        binding.apply {
            postBtn.setOnClickListener {
                viewModel.addComment(editContent.text.toString())
                listener.openFragment(CommentListFragment())
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearEvents()
    }
}
