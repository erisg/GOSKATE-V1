package go.goskate.goskate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import go.goskate.goskate.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()

    }

    fun setUpNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navigation_goskate) as NavHostFragment?
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment!!.navController
        )
    }
}