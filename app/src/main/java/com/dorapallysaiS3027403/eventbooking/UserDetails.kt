package com.dorapallysaiS3027403.eventbooking

import android.content.Context

object UserDetails {

    private const val USERDETAILS_PREF = "EventBooking"

    fun saveUserLoginStatus(context: Context, value: Boolean) {
        val userLoginSP = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        val editor = userLoginSP.edit()
        editor.putBoolean("LOGIN_STATUS", value).apply()
    }

    fun getUserLoginStatus(context: Context): Boolean {
        val userLoginSP = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        return userLoginSP.getBoolean("LOGIN_STATUS", false)
    }

    fun saveName(context: Context, name: String) {
        val userName = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        val editor = userName.edit()
        editor.putString("USER_NAME", name).apply()
    }

    fun getName(context: Context): String? {
        val userName = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        return userName.getString("USER_NAME", null)
    }

    fun saveGender(context: Context, gender: String) {
        val userGender = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        val editor = userGender.edit()
        editor.putString("USER_GENDER", gender).apply()
    }

    fun getGender(context: Context): String? {
        val userGender = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        return userGender.getString("USER_GENDER", null)
    }


    fun saveEmail(context: Context, email: String) {
        val userEmail = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        val editor = userEmail.edit()
        editor.putString("USER_EMAIL", email).apply()
    }

    fun getEmail(context: Context): String? {
        val userEmail = context.getSharedPreferences(USERDETAILS_PREF, Context.MODE_PRIVATE)
        return userEmail.getString("USER_EMAIL", null)
    }
}
