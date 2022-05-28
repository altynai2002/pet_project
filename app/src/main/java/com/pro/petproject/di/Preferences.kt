package com.pro.petproject.di

import android.content.Context


interface Preferences {
    fun saveEmail(email: String)

    fun savePassword(password: String)

    fun getEmail(email: String): String
    fun getPassword(password: String): String
}

class PreferencesImpl(context: Context) : Preferences {
    private val preferences = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)

    override fun saveEmail(email: String) {
        preferences.edit().apply {
            putString(EMAIL, email)
        }.apply()
    }

    override fun savePassword(password: String) {
        preferences.edit().apply {
            putString(PASSWORD, password)
        }.apply()
    }

    override fun getEmail(email: String): String {
        return preferences.getString(EMAIL, "") ?: ""
    }

    override fun getPassword(password: String): String {
        return preferences.getString(PASSWORD, "") ?: ""
    }

    companion object {
        const val EMAIL = "EMAIL"
        const val PASSWORD = "PASSWORD"
    }

}