package com.pro.petproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pro.petproject.databinding.FragmentPostlistBinding
import com.pro.petproject.ui.base.BaseFragment
import com.pro.petproject.ui.base.BaseViewModel
import com.pro.petproject.ui.main.MainViewModel
import com.pro.petproject.ui.main.rv.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment: BaseFragment<MainViewModel>(MainViewModel::class.java) , ItemAdapter.Listener {
    private lateinit var listener : Navigate
    private var _binding: FragmentPostlistBinding? = null
    private val binding get() = _binding!!
    private val epAdapter: ItemAdapter = ItemAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Navigate
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPostlistBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostlistBinding.bind(view)
        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews(){
        with(binding){
            recycler.adapter = epAdapter
            val layoutManager = LinearLayoutManager(activity)
            recycler.layoutManager = layoutManager
            recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            swiperefresh.setOnRefreshListener {
//                val list = mutableListOf<String>()
//                for (i in 0..20) {
//                    list.add("ITEM -$i")
//                }
//                epAdapter.setData(list)
                viewModel.loadPosts()
            }
        }
    }

    private fun subscribeToLiveData(){
        viewModel.posts.observe(viewLifecycleOwner) {
            epAdapter.setData(it)
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

    //  переход на detail fragment
    override fun onClick(index: Int) {
//        viewModel.getPostByIndex(index)?.let {
//            listener.openFragment(PostFragment.newInstance(it.id!!))
//        }
    }
}
