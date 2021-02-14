package com.fuzaylofficial.skyweb.customutlis

import android.content.Context
import com.fuzaylofficial.skyweb.R

object PasswordValidator {
    fun isValid(ctx:Context?,password: String): PasswordValid {
        var valid =
            PasswordValid(true, "Valid")
        if (password.length !in 6..20) {
            valid = PasswordValid(
                false,
                ctx!!.resources.getString(R.string.pas_lenth)
            )
        }
        if (password.contains(" ")) {
            valid = PasswordValid(
                false,
                ctx!!.resources.getString(R.string.pas_space)
            )
        }
        if (!password.matches(Regex("^(?=.*\\d).+$"))) {
            valid = PasswordValid(
                false,
                ctx!!.resources.getString(R.string.pas_digit)
            )
        }
        if (!password.matches(Regex("^(?=.*[A-Z]).+$"))) {
            valid = PasswordValid(
                false,
                ctx!!.resources.getString(R.string.pas_uppercase)
            )
        }
        if (!password.matches(Regex("^(?=.*[a-z]).+$"))) {
            valid = PasswordValid(
                false,
                ctx!!.resources.getString(R.string.pas_lowercase)
            )
        }
        return valid
    }
}