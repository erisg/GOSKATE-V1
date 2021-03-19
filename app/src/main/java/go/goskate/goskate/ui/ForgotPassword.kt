package go.goskate.goskate.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.forgot_password.*

class ForgotPassword : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        recoverButton.setOnClickListener {
            validateDataComplete()
        }
    }

    private fun validateDataComplete() {
        var email = emailRecoverEditText.text.toString()

        if (email.isNotEmpty()) {
            authViewModel.dataRecoverAccount(email).observe(this, {
                if (it == "Por favor revisa tu correo para restablecer la contrase;a") {
                    Toast.makeText(
                        this,
                        "POR FAVOR REVISA TU CORREO PARA RESTABLECER LA CONTRASE;A",
                        Toast.LENGTH_LONG
                    ).show()
                    email = ""
                    startActivity(Intent(this, Login::class.java))
                } else {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "DEBE INGRESAR UN CORREO", Toast.LENGTH_LONG).show()
        }
    }
}