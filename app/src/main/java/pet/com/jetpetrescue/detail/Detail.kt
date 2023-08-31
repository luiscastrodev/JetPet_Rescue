package pet.com.jetpetrescue.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.data.DummyPetDataSource
import hoods.com.jetpetrescue.data.model.Pet
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.components.OwnerCardInfo
import pet.com.jetpetrescue.components.PetBasicInfo
import pet.com.jetpetrescue.components.PetInfoItem

@Composable
fun DetailScreen(
    index: Int,
    onNavigate: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detail")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier =
                        Modifier
                            .size(36.dp, 36.dp)
                            .clickable {
                                onNavigate.invoke()
                            },
                        tint = MaterialTheme.colors.onSurface
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 0.dp
            )
        }
    ) { paddingValues ->
        val pet = DummyPetDataSource.dogList[index]
        LazyColumn(contentPadding = paddingValues) {
            item {
                Image(
                    painter = painterResource(id = pet.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop
                )

                PetBasicInfo(pet.name, pet.location, pet.breed)
            }
            item {
                MyStoryItem(pet)
            }

            item {
                PetInfo(pet = pet)
            }
            item{
                OwnerCardInfo(owner = pet.owner)
            }
        }
    }
}

@Composable
fun PetInfo(pet: Pet) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "Pet Info")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoCard(primaryText = pet.age, secondaryText = "Age", modifier = Modifier
                .weight(1f)
                .padding(4.dp))
            InfoCard(primaryText = pet.color, secondaryText = "color", modifier = Modifier
                .weight(1f)
                .padding(4.dp))
            InfoCard(primaryText = pet.breed, secondaryText = "Breed", modifier = Modifier
                .weight(1f)
                .padding(4.dp))

        }
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.disabled
            ) {
                Text(
                    text = secondaryText
                )
            }
            Text(
                text = primaryText,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(4.dp))

        }
    }
}

@Composable
fun MyStoryItem(
    pet: Pet
) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title("My Story")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = pet.description, modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun Title(
    title: String
) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
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
            DetailScreen(index = 0) {}
        }
    }
}
