package com.pro.petproject.ui.comment

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
import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.databinding.FragmentPostlistBinding
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.Navigate
import com.pro.petproject.ui.base.BaseFragment
import com.pro.petproject.ui.main.rv.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CommentListFragment : BaseFragment<CommentListViewModel>(CommentListViewModel::class.java),
    CommentAdapter.Listener {
    private lateinit var listener : Navigate
    private var _binding: FragmentPostlistBinding? = null
    private val binding get() = _binding!!
    private val commentAdapter: CommentAdapter = CommentAdapter(this)

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
            recycler.adapter = commentAdapter
            val layoutManager = LinearLayoutManager(activity)
            recycler.layoutManager = layoutManager
            recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            swiperefresh.setOnRefreshListener {
                viewModel.loadComments()
            }
        }
    }

    private fun subscribeToLiveData(){
        viewModel.comments.observe(viewLifecycleOwner) {
            commentAdapter.setData(it)
        }

        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
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

    //    то же самое что мы делали в mainactivity
    companion object{
        fun newInstance(id: String): CommentListFragment {
            val args = Bundle().apply { putString(Long::class.java.canonicalName, id) }
            return CommentListFragment().apply { arguments = args }
        }
    }

    //  переход на detail fragment
    override fun onClick(index: Int) {
//        viewModel.getPostByIndex(index)?.let {
//            listener.openFragment(PostFragment.newInstance(it.id!!))
//        }
    }
}
