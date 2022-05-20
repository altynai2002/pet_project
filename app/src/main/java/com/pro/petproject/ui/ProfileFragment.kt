package com.pro.petproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pro.petproject.R
import com.pro.petproject.databinding.FragmentProfileBinding
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
                is Event.FetchedEpisode -> setDetail(it)
                else -> {}
            }
        }
    }

    private fun setDetail(it: Event.FetchedEpisode) {
        Log.d("Profile", it.toString())
        binding.gender.text = it.user.gender
//        binding.season.text = "season: " + it.ep.season
//        binding.airDate.text = "air date: " + it.ep.air_date
//        binding.episode.text = "episode: " + it.ep.episode
//        binding.series.text = "series: ${it.ep.series}"
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