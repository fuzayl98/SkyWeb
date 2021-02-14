package com.fuzaylofficial.skyweb.ui.auth

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fuzaylofficial.skyweb.R
import com.fuzaylofficial.skyweb.customutlis.StringUtil
import com.fuzaylofficial.skyweb.databinding.FragmentAuthBinding
import com.fuzaylofficial.skyweb.di.ViewModelFactory
import com.fuzaylofficial.skyweb.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class AuthFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var authBinding: FragmentAuthBinding

    private lateinit var authViewModel: AuthViewModel

    lateinit var alertDialog: AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        authViewModel = ViewModelProvider(this,viewModelFactory).get(AuthViewModel::class.java)
        authBinding = FragmentAuthBinding.inflate(inflater,container,false)

        authBinding.loginEdt.addTextChangedListener(datavalidation(authBinding.loginEdt))
        authBinding.passwordEdt.addTextChangedListener(datavalidation(authBinding.passwordEdt))
        authBinding.loginbutton.setOnClickListener { getWeather() }

        alertDialog = AlertDialog.Builder(context).setView(R.layout.dialog).create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        authViewModel.text.observe(viewLifecycleOwner, Observer { s ->

            alertDialog.dismiss()
            if (s!=null){
                Snackbar.make(authBinding.root,
                    StringUtil.weatherToString(s),Snackbar.LENGTH_SHORT).show()
            }else{
                context?.resources?.getText(R.string.weather_error)?.let {
                    Snackbar.make(authBinding.root,
                        it,Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        return authBinding.root
    }

    private fun getWeather(){
        if (getValidation()){
            authBinding.loginEdt.onEditorAction(EditorInfo.IME_ACTION_DONE)
            authBinding.passwordEdt.onEditorAction(EditorInfo.IME_ACTION_DONE)
            alertDialog.show()
            authViewModel.getWeahter()
        }else{

            Toast.makeText(context,"No no no",Toast.LENGTH_SHORT).show()
        }
    }

    fun getValidation():Boolean{
        return validateEmail(
            loginLayout = authBinding.emailLayout,
            login = authBinding.loginEdt)&&
                validatePassword(
               pasLayout = authBinding.passwordLayout,
                    pas = authBinding.passwordEdt
                )
    }

    inner class datavalidation(private var view: View):TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when(view.id){
                R.id.login_edt -> {validateEmail(authBinding.emailLayout,authBinding.loginEdt)}
                R.id.password_edt -> {validatePassword(authBinding.passwordLayout,authBinding.passwordEdt)}
            }
        }
    }

    private fun validatePassword(pasLayout:TextInputLayout,pas:TextInputEditText):Boolean{
        return if (isValidPassword(pas.text.toString()).isValidate){
            pasLayout.error = isValidPassword(pas.text.toString()).validMessafe
            pasLayout.isErrorEnabled  = false
            true
        }else{
            pasLayout.error = isValidPassword(pas.text.toString()).validMessafe
            pas.requestFocus()
            false
        }
    }

    private fun validateEmail(loginLayout:TextInputLayout,login:TextInputEditText): Boolean {
        if (login.text.toString().trim().isEmpty()) {
            loginLayout.error = context?.resources?.getString(R.string.req_field)
            login.requestFocus()
            return false
        } else if (!isValidEmail(login.text.toString())) {
            loginLayout.error = context?.resources?.getString(R.string.invalid_email)
            login.requestFocus()
            return false
        } else {
            loginLayout.isErrorEnabled = false
        }
        return true
    }

}