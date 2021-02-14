package com.fuzaylofficial.skyweb.ui.base

import android.text.TextUtils
import android.util.Patterns
import com.fuzaylofficial.skyweb.customutlis.PasswordValid
import com.fuzaylofficial.skyweb.customutlis.PasswordValidator
import dagger.android.support.DaggerFragment

abstract class BaseFragment: DaggerFragment() {
    fun isValidEmail(it: String): Boolean{
        return !TextUtils.isEmpty(it) && Patterns.EMAIL_ADDRESS.matcher(it).matches()
    }

    fun isValidPassword(it:String): PasswordValid {
        return PasswordValidator.isValid(context,it)
    }
}