package pet.com.jetpetrescue.presentation.detail

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.domain.models.Pet
import pet.com.jetpetrescue.presentation.components.PetBasicInfo

@Composable
fun DetailScreen(
    pet: Pet,
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
        var isLoading: Boolean by remember {
            mutableStateOf(false)
        }
        LazyColumn(contentPadding = paddingValues) {
            item {
                if (isLoading) {
                    CircularProgressIndicator()
                }

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        //.clip(RoundedCornerShape(16.dp))
                    ,
                    model = if (pet.photos.isNotEmpty()) pet.photos[0].full
                    else null,
                    placeholder = painterResource(id = R.drawable.placeholder_ic),
                    error = painterResource(id = R.drawable.placeholder_ic),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart,
                    onLoading = {
                        isLoading = true
                    },
                    onError = {
                        it.result.throwable.printStackTrace()
                    },
                    onSuccess = {
                        isLoading = false
                    }
                )

                PetBasicInfo(pet.name, pet.distance, pet.breeds)
            }
            item {
                MyStoryItem(pet)
            }

            item {
                PetInfo(pet = pet)
            }
            // item {
            //    OwnerCardInfo(owner = pet.owner)
            //}
            item {
                PetButton() {

                }
            }
        }
    }
}

@Composable
fun PetButton(onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(36.dp))

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Adopt Me")
    }
    Spacer(modifier = Modifier.height(24.dp))
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
            InfoCard(
                primaryText = pet.age, secondaryText = "Age", modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.colors, secondaryText = "color", modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.colors, secondaryText = "Breed", modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )

        }
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String,
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
        }
    }
}
