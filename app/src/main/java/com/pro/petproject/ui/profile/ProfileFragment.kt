package com.pro.petproject.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pro.petproject.databinding.FragmentProfileBinding
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.Navigate
import com.pro.petproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<ProfileViewModel>(ProfileViewModel::class.java)  {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener : Navigate

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is Event.FetchedUser -> setDetail(it)
                else -> {}
            }
        }
    }

    private fun setDetail(it: Event.FetchedUser) {
        Log.d("Profile", it.toString())
        binding.gender.text = it.user.gender
    }

    //    то же самое что мы делали в mainactivity
//    companion object{
//        fun newInstance(id: Long): EpisodeFragment {
//            val args = Bundle().apply { putLong(Long::class.java.canonicalName, id) }
//            return EpisodeFragment().apply { arguments = args }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}