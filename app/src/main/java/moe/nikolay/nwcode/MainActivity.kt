package moe.nikolay.nwcode

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import moe.nikolay.nwcode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val bottomNavigationView: BottomNavigationView = binding.navView
        bottomNavigationView.setupWithNavController(navController)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_settings))

        //fix refresh when click on current tab
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            if(item.itemId != bottomNavigationView.selectedItemId)
                NavigationUI.onNavDestinationSelected(item, navController)
            true
        }

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

}