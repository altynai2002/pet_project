package com.pro.petproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pro.petproject.R
import com.pro.petproject.databinding.FragmentPostlistBinding
import com.pro.petproject.ui.base.BaseFragment
import com.pro.petproject.ui.comment.CommentListFragment
import com.pro.petproject.ui.main.MainViewModel
import com.pro.petproject.ui.main.rv.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment: BaseFragment<MainViewModel>(MainViewModel::class.java) , ItemAdapter.Listener {
    private lateinit var listener : Navigate
    private var _binding: FragmentPostlistBinding? = null
    private val binding get() = _binding!!
    private val postAdapter: ItemAdapter = ItemAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Navigate
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPostlistBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostlistBinding.bind(view)

        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        view.visibility = View.VISIBLE

        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews(){
        with(binding){
            recycler.adapter = postAdapter
            val layoutManager = LinearLayoutManager(activity)
            recycler.layoutManager = layoutManager
            recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            swiperefresh.setOnRefreshListener {
                viewModel.loadPosts()
            }
        }
    }

    private fun subscribeToLiveData(){
        viewModel.posts.observe(viewLifecycleOwner) {
            postAdapter.setData(it)
        }

        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
//                is Event.ShowToast -> showToast(getString(it.resId))
                is Event.ShowLoading -> binding.swiperefresh.isRefreshing = true
                is Event.HideLoading -> binding.swiperefresh.isRefreshing = false
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearEvents()
    }

    //  переход на comment fragment
    override fun onClick(index: String) {
        viewModel.getPostByIndex(index)?.let {
            listener.openFragment(CommentListFragment.newInstance(it))
        }
    }

}
