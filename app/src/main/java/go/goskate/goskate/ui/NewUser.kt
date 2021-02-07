package go.goskate.goskate.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import go.goskate.goskate.MainActivity
import go.goskate.goskate.R
import go.goskate.goskate.helper.CaptureProfilePhotoDialogFragment
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.register.*
import java.text.SimpleDateFormat
import java.util.*


class NewUser : AppCompatActivity() {


    private val authViewModel: AuthViewModel by viewModels()
    lateinit var profileImage: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        authViewModel.profileImage.observeForever {
            profileImageView.setImageBitmap(it)
            profileImage = it
        }

        menRadioButton.setOnClickListener {
            authViewModel.userVO.userGender = true
        }

        womenRadioButton.setOnClickListener {
            authViewModel.userVO.userGender = false
        }

        /**
         * Abre la camara y se captura foto de perfil
         */

        addPhotoProfileFloatingActionButton.setOnClickListener {
            captureImageProfile()
        }

        /**
         * Valida que la informacion este completa , si esta completa envia usuario a viewmodel
         */

        registerButton.setOnClickListener {
            validateDataComplete()
        }
    }


    private fun validateDataComplete() {

        val myCalendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val nameFile = formatter.format(myCalendar.time)

        val userName = nameEditText.text.toString()
        val userEmail = emailEditText.text.toString()
        val userPassword = passwordEditText.text.toString()
        val userPasswordConfirm = confirmPasswordEditText.text.toString()
        val userTelephone = telephoneEditText.text.toString()
        val userAge = ageEditText.text.toString()

        if (userName.isNotEmpty() && userEmail.isNotEmpty() && userPassword.isNotEmpty() && userPasswordConfirm.isNotEmpty()
            && userTelephone.isNotEmpty() && userAge.isNotEmpty() && profileImage != null
        ) {
            if (userPassword == userPasswordConfirm) {
                authViewModel.userVO.userPassword = userPassword
                authViewModel.userVO.userName = userName
                authViewModel.userVO.userEmail = userEmail
                authViewModel.userVO.userTelephone = userTelephone
                authViewModel.userVO.userAge = userAge
                val path: String =
                    Images.Media.insertImage(this.contentResolver, profileImage, nameFile, userName)
                authViewModel.userVO.profileImage = path
                authViewModel.dataNewUser().observe(this, {
                    if (it == "Successful") {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "LAS CONTRASENAS NO COINCIDEN", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "FALTA INFORMACION POR INGRESAR", Toast.LENGTH_LONG).show()
        }
    }

    private fun captureImageProfile() {
        val dialog = CaptureProfilePhotoDialogFragment()
        dialog.show(this.supportFragmentManager, "PostDialog")

    }


}