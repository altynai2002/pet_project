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
import com.google.android.material.textfield.TextInputLayout
import com.pro.petproject.R
import com.pro.petproject.databinding.FragmentRegistrationBinding
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
    private lateinit var emailTxt: String
    private lateinit var passwordTxt: String
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout

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

        emailInputLayout = binding.editLayout2
        passwordInputLayout = binding.editLayout3

        binding.radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            genderInt = when(checkedId) {
                R.id.male -> "Male"
                R.id.female -> "Female"
                else -> ""
            }
        }

        binding.apply{
            registrationBtn.setOnClickListener {
                checkInput()
            }
        }
    }

    private fun FragmentRegistrationBinding.checkInput() {
        if (!isValidEmail(email.text.toString())) {
            Toast.makeText(activity, "Неккоректная почта", Toast.LENGTH_SHORT).show()
        } else {
            emailTxt = email.text.toString()
            passwordTxt = password.text.toString()
            listener.openFragment(PostListFragment())
            viewModel.registerNewUser(emailTxt, passwordTxt)
            Log.e("TAG", "set email = $emailTxt, editTxt = $email password = $password")
        }
    }


    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}