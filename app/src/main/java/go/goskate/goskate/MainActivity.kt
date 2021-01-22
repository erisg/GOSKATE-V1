package go.goskate.goskate

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IMenuGone {
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }

    override fun menuGone() {
        bottomNavigationView.visibility = View.GONE
    }


}