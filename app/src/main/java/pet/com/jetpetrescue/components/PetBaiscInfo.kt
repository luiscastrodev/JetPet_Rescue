package pet.com.jetpetrescue.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.ui.theme.JetPetTheme


@Composable
fun PetBasicInfo(
    name: String,
    gender: String,
    location: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.align(CenterVertically)) {
            Text(
                text = name, modifier = Modifier.padding(end = 12.dp),
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp, 16.dp),
                    tint = Color.Red
                )
                Text(
                    text = location,
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
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Adoptable",
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption
            )

        }

        Column(
            modifier = Modifier.height(80.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenderTag(gender = gender)
            Text(
                text = "Dog",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption
            )
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
private fun PetBasicInfoPreview() {
    JetPetTheme {
        Surface {
            PetBasicInfo("Pet", "male", "Canada")
        }
    }
}