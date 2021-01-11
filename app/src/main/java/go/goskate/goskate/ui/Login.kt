package go.goskate.goskate.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import go.goskate.goskate.MainActivity
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}