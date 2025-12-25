package com.example.tbc_collaboration_2025.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.example.tbc_collaboration_2025.R
import com.example.tbc_collaboration_2025.common.Ids
import com.example.tbc_collaboration_2025.presentation.extension.launchAndRepeatOnStart
import com.example.tbc_collaboration_2025.presentation.screen.splash.SplashContract.SideEffect.*
import com.example.tbc_collaboration_2025.presentation.screen.splash.SplashViewModel
import com.example.tbc_collaboration_2025.databinding.ActivityMainBinding as Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: Binding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState = savedInstanceState)
        enableEdgeToEdge() // for jetpack compose, this line should be placed before super.onCreate and setContent
        _binding = Binding.inflate(layoutInflater)
        setContentView(view = binding.root)
        splashScreen.setKeepOnScreenCondition { viewModel.state.value.isLoading }
        setupNavigation()
    }

    private fun setupNavigation() {
        launchAndRepeatOnStart {
            viewModel.sideEffect.collect { sideEffect ->
                val navHostFragment = supportFragmentManager
                    .findFragmentById(Ids.navHostFragment) as NavHostFragment
                val navController = navHostFragment.navController
                val navGraph = navController.navInflater.inflate(graphResId = R.navigation.nav_graph)

                when (sideEffect) {
                    NavigateToEventHub -> navGraph.setStartDestination(Ids.eventHubFragment)
                    NavigateToSignUp -> navGraph.setStartDestination(Ids.createAccountFragment)
                }

                navController.graph = navGraph
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
