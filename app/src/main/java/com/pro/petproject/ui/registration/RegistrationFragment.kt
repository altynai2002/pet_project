package com.pro.petproject.ui.registration

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pro.petproject.R
import com.pro.petproject.databinding.FragmentRegistrationBinding
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.Navigate
import com.pro.petproject.ui.PostListFragment
import com.pro.petproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment: BaseFragment<RegistrationViewModel>
    (RegistrationViewModel::class.java)   {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener : Navigate
    var genderInt: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Navigate
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        view.visibility = View.GONE

        subscribeToLiveData()
//        setSpinner()

        binding.radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            genderInt = when(checkedId) {
                R.id.male -> "Male"
                R.id.female -> "Female"
                else -> ""
            }
        }

        binding.apply{
            registrationBtn.setOnClickListener {
                if (!isValidEmail(email.text.toString())) {
                    Toast.makeText(activity, "Неккоректная почта", Toast.LENGTH_SHORT).show()
                } else {
                    listener.openFragment(PostListFragment())
                }
            }
        }
    }

//    private fun setSpinner() {
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.gender,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.gender.adapter = adapter
//        }
//    }

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
        binding.apply{
            genderInt = it.user.gender
//            email.text = it.user.email
        }

    }

    //    то же самое что мы делали в mainactivity
//    companion object{
//        fun newInstance(id: Long): EpisodeFragment {
//            val args = Bundle().apply { putLong(Long::class.java.canonicalName, id) }
//            return EpisodeFragment().apply { arguments = args }
//        }
//    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}