package pet.com.jetpetrescue.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.domain.models.Pet

@Composable
fun PetInfoItem(
    pet: Pet,
    onPetItemClick: (Pet) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {
                onPetItemClick.invoke(pet)
            }),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {

        var isLoading: Boolean by remember {
            mutableStateOf(false)
        }
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            //.background(MaterialTheme.colors.background)
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {


                if (isLoading) {
                    CircularProgressIndicator()
                }

                AsyncImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    model = if (pet.photos.isNotEmpty()) pet.photos[0].medium
                    else null,
                    placeholder = painterResource(id = R.drawable.placeholder_ic),
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
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = pet.name,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = buildString {
                            append(pet.age)
                            append("|")
                            append(pet.breeds)
                        },
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.caption
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(16.dp, 16.dp)
                        )
                        Text(
                            text = pet.contact.address,
                            modifier = Modifier.padding(
                                start = 8.dp,
                                top = 0.dp,
                                end = 12.dp,
                                bottom = 0.dp
                            ),
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                GenderTag(gender = pet.gender)
                Text(
                    text = "Adoptable",
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.caption
                )
            }

        }
    }

}

@Composable
fun GenderTag(
    gender: String,
    modifier: Modifier = Modifier
) {
    val color = if (gender == "male") {
        Color.Blue
    } else {
        Color.Red
    }

    Box(
        modifier =
        modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = .2f))
    ) {
        Text(
            text = gender,
            modifier = Modifier.padding(12.dp, 4.dp, 12.dp, 6.dp),
            style = MaterialTheme.typography.caption,
            color = color
        )
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
private fun PetInfoItemPreview() {
    JetPetTheme {
        Surface {
            //  PetInfoItem(DummyPetDataSource.dogList.random()) {}
        }
    }
}
