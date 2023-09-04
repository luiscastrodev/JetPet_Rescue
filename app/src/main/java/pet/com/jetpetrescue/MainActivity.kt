package hoods.com.jetpetrescue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.presentation.detail.DetailScreen
import pet.com.jetpetrescue.presentation.home.Home
import pet.com.jetpetrescue.presentation.navitagion.JetPetNavigation
import pet.com.jetpetrescue.presentation.viewmodels.MainViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var isDarkTheme by remember {
                mutableStateOf(false)
            }

            val viewModel = viewModel(modelClass = MainViewModel::class.java)

            var navController = rememberNavController()

            JetPetTheme(
                darkTheme = isDarkTheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    JetPetNavigation(
                        navController = navController,
                        uiState = viewModel.uiState,
                        onThemeChange = {
                            isDarkTheme = !isDarkTheme
                        },
                        onLoadNexPage = {
                            viewModel.loadingNexPage()
                        },
                        onInfiniteScrollChange = {
                            viewModel.onInfiniteScollchange(it)
                        }
                    )
                }
            }
        }
    }
}
