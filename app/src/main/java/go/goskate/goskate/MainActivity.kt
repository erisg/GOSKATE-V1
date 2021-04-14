package go.goskate.goskate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import go.goskate.goskate.ui.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.maps_fragment.*


class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        val prefs = getSharedPreferences("preference", Context.MODE_PRIVATE).edit()
        prefs.putString("email", authViewModel.userVO.userEmail)
        prefs.putString("password", authViewModel.userVO.userPassword)
        prefs.apply()

    }

}