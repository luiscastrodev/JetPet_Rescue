package pet.com.jetpetrescue.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.ui.theme.JetPetTheme

@Composable
fun PetInfoItem() {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
    ) {
        Row {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = R.drawable.blue_dog),
                contentDescription = "blue dog",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Peter",
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = buildString {
                        append("Adult")
                        append("|")
                        append("Domestic Short Hair")
                    },
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.caption
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Icon location",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp, 16.dp)
                    )
                    Text(
                        text = "Toronto US",
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
        Column {

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
private fun PetInfoItemPreview() {
    JetPetTheme {
        Surface {
            PetInfoItem()
        }
    }
}
