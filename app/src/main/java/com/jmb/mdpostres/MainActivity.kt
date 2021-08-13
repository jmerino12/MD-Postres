package com.jmb.mdpostres

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.jmb.mdpostres.databinding.ActivityMainBinding
import com.jmb.mdpostres.databinding.BottomSheetExitBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetExitBinding: BottomSheetExitBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val configuration = AppBarConfiguration.Builder(navController.graph).build()
        NavigationUI.setupWithNavController(binding.toolbar, navController, configuration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.toolbar.title = destination.label
            binding.toolbar.navigationIcon = null
        }

        bottomSheetExitBinding = binding.sheetExit

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetExitBinding.root)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetExitBinding.btnExit.setOnClickListener {
            finish()
        }
        bottomSheetExitBinding.btnClose.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*if (item.itemId == R.id.action_confirmation) {
            navController.navigate(R.id.action_global_confirmation)
        }*/

        //return super.onOptionsItemSelected(item)
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}