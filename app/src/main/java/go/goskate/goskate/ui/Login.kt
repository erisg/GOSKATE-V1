package go.goskate.goskate.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import go.goskate.goskate.MainActivity
import go.goskate.goskate.R
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.login.*

class Login : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        forgotPasswordTextView.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }

        authGoogleImageView.setOnClickListener {
            //  singInWhitGoogle()
        }

        loginButton.setOnClickListener {
            validateData()
        }

        haveAccountTextView.setOnClickListener {
            startActivity(Intent(this, NewUser::class.java))
        }

    }


    //TODO corregir bug
    private fun singInWhitGoogle() {
        val googleConfig = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, googleConfig)
        startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
    }

    private fun session() {
        val prefs = getSharedPreferences("preference", Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        if (email != null && password != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        session()
    }

    private fun validateData() {
        val email = nameOrEmailEditText.text.toString()
        val password = passwordEditTex.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            dataResponse(email, password)
        } else {
            Toast.makeText(this, "FALTA INFORMACION POR INGRESAR", Toast.LENGTH_LONG).show()
        }
    }

    private fun dataResponse(email: String, password: String) {
        authViewModel.dataLogin(email, password).observeForever {
            if (it == "Successful") {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.result
            if (account != null) {
                authViewModel.dataLoginWithCredentials(account).observeForever {
                    if (it == "Successful") {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}