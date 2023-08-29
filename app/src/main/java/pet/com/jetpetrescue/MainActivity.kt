package hoods.com.jetpetrescue

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.components.PetInfoItem


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPetTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = MaterialTheme.colors.background
                ) {
                    PetInfoItem()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(
    name = "Light mode",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, name = "Dark Mode"
)
@Composable
private fun AppPreview() {
    JetPetTheme {
        Surface {
            Greeting("ANDROID")
        }
    }
}
