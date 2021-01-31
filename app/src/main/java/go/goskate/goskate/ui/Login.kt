package go.goskate.goskate.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import go.goskate.goskate.MainActivity
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.login.*

class Login : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        loginButton.setOnClickListener {
            validateData()
            startActivity(Intent(this, MainActivity::class.java))
        }

        haveAccountTextView.setOnClickListener {
            startActivity(Intent(this, NewUser::class.java))
        }

    }

    private fun validateData() {
        val email = nameOrEmailEditText.text.toString()
        val password = passwordEditTex.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            authViewModel.userVO.userEmail = email
            authViewModel.userVO.userPassword = password
            authViewModel.dataLogin()
        }
    }
}