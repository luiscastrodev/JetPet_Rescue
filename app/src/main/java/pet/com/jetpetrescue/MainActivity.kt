package hoods.com.jetpetrescue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.detail.DetailScreen
import pet.com.jetpetrescue.home.Home


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Home(
                        onPetClick = {

                        },
                        onSwitchClick = {

                        }
                    )
                }
            }
        }
    }
}

