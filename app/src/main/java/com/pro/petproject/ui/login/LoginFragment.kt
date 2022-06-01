package com.pro.petproject.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.pro.petproject.databinding.FragmentLoginBinding
import com.pro.petproject.ui.Event
import com.pro.petproject.ui.Navigate
import com.pro.petproject.ui.PostListFragment
import com.pro.petproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel>
    (LoginViewModel::class.java)   {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener : Navigate

    private lateinit var emailInputLayoutLogin: TextInputLayout
    private lateinit var passwordInputLayoutLogin: TextInputLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Navigate
        } catch (e: Exception){ print("Activity must implement FragmentListener")}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailInputLayoutLogin = binding.editLayout
        passwordInputLayoutLogin = binding.editLayout2

        val emailLogin = binding.email
        val passwordLogin = binding.password
        binding.apply {
            btnLogin.setOnClickListener {
            if (isValidEmail(email.text.toString())) {
                login(emailLogin.text.toString(), passwordLogin.text.toString())
                listener.openFragment(PostListFragment())
            } else if (!isValidEmail(email.text.toString())) {
                Toast.makeText(activity, "Неккоректная почта", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Почта или пароль не совпадают", Toast.LENGTH_SHORT).show()
            }
                Log.e("TAG", "set email = $email, editTxt = $email")
            }

        }

    }

    private fun login(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(email, password)

        }
        else if(email.isEmpty()) {
            emailInputLayoutLogin.error = "Введите почту!"
        }
        else if (password.isEmpty()) {
            passwordInputLayoutLogin.error = "Введите пароль!"
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