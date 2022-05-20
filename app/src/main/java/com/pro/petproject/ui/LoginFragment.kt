package com.pro.petproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pro.petproject.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel>
    (LoginViewModel::class.java)   {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener : Navigate

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater)
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
//        binding.gender.text = it.user.gender
//        binding.email.text = "season: " + it.ep.season
//        binding.password.text = "air date: " + it.ep.air_date
//        binding.name.text = "episode: " + it.ep.episode
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