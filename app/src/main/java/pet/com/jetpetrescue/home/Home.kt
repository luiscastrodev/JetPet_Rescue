package pet.com.jetpetrescue.home

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetpetrescue.data.DummyPetDataSource
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.components.PetInfoItem
import pet.com.jetpetrescue.components.TopBar

@Composable
fun Home(
    onSwitchClick: () -> Unit,
    onPetClick: (Int) -> Unit
) {
    val petList = DummyPetDataSource.dogList.shuffled()

    Scaffold(
        topBar = {
            TopBar {
                onSwitchClick()
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(petList) { index, pet ->
                PetInfoItem(
                    pet,
                    onPetItemClick = {
                        onPetClick(index)
                    }
                )
            }
        }
    }
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
private fun HomePreview() {
    JetPetTheme {
        Surface {
            Home({}, {})
        }
    }
}

