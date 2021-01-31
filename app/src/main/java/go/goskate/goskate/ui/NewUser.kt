package go.goskate.goskate.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import go.goskate.goskate.MainActivity
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.register.*

class NewUser : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        registerButton.setOnClickListener {
            validateDataComplete()
        }
    }


    fun validateDataComplete() {
        val userName = nameEditText.text.toString()
        val userEmail = emailEditText.text.toString()
        val userPassword = passwordEditText.text.toString()
        val userPasswordConfirm = confirmEditText.text.toString()
        val userTelephone = telephoneEditText.text.toString()
        val userAge = ageEditText.text.toString()

        if (userName.isNotEmpty() && userEmail.isNotEmpty() && userPassword.isNotEmpty() && userPasswordConfirm.isNotEmpty()
            && userTelephone.isNotEmpty() && userAge.isNotEmpty()
        ) {
            if (userPassword == userPasswordConfirm) {
                authViewModel.userVO.userPassword = userPassword
            }
            authViewModel.userVO.userName = userName
            authViewModel.userVO.userEmail = userEmail
            authViewModel.userVO.userTelephone = userTelephone
            authViewModel.userVO.userAge = userAge
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "FALTA INFORMACION POR INGRESAR", Toast.LENGTH_LONG).show()
        }
    }
}