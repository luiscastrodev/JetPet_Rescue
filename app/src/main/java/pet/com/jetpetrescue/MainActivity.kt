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
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.detail.DetailScreen
import pet.com.jetpetrescue.home.Home


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

            JetPetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    when (currentScreen) {
                        Screen.Home -> {
                            Home(
                                onPetClick = { index ->
                                    seletedIndex = index
                                    currentScreen = Screen.Detail
                                },
                                onSwitchClick = {

                                }
                            )
                        }

                        Screen.Detail -> {
                            DetailScreen(index = seletedIndex) {
                                currentScreen = Screen.Home
                            }
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