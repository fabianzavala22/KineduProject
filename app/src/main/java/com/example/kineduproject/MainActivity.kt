package com.example.kineduproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kineduproject.di.ActivityComponent

class MainActivity : AppCompatActivity() {

    private lateinit var  navController: NavController
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as MyApplication).appComponent.activityComponent().create()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
