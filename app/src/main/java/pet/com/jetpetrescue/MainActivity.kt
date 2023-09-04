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
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.presentation.detail.DetailScreen
import pet.com.jetpetrescue.presentation.home.Home
import pet.com.jetpetrescue.presentation.viewmodels.MainViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var currentScreen by remember {
                mutableStateOf(Screen.Home)
            }

            var seletedIndex by remember {
                mutableStateOf(-1)
            }

            var isDarkTheme by remember {
                mutableStateOf(false)
            }

            val viewModel = viewModel(modelClass = MainViewModel::class.java)

            var id by remember {
                mutableStateOf(-1)
            }

            JetPetTheme(
                darkTheme = isDarkTheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    when (currentScreen) {
                        Screen.Home -> {
                            Home(
                                onLoadNextPage = {
                                    viewModel.loadingNexPage()
                                },
                                onInfinitScollingChange = {
                                    viewModel.onInfiniteScollchange(it)
                                },
                                uiState = viewModel.uiState,
                                onPetClick = { index ->
                                    seletedIndex = index
                                    currentScreen = Screen.Detail
                                },
                                onSwitchClick = {
                                    isDarkTheme = !isDarkTheme
                                }
                            )
                        }

                        Screen.Detail -> {

                            DetailScreen(
                                onNavigate = { currentScreen = Screen.Home },
                                pet = viewModel.uiState.animal.data?.get(seletedIndex)!!
                            )

                        }

                    }

                }
            }
        }
    }
}

enum class Screen {
    Home,
    Detail
}