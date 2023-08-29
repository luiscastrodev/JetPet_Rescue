package pet.com.jetpetrescue.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.ui.theme.JetPetTheme

@Composable
fun TopBar(
    onSwitchToggle: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Hey Pet",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Find a new friend near you to adopt",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
                    end = 36.dp
                )
        ) {
            PetSwitch {
                onSwitchToggle.invoke()
            }
        }
    }
}


@Composable
fun PetSwitch(onSwitchToggle: () -> Unit) {
    val icon = if (isSystemInDarkTheme()) {
        painterResource(id = R.drawable.ic_switch_on)
    } else painterResource(id = R.drawable.ic_switch_off)

    Icon(
        modifier = Modifier
            .size(24.dp, 24.dp)
            .clickable(onClick = {
                onSwitchToggle()
            }),
        tint = MaterialTheme.colors.onSurface,
        painter = icon,
        contentDescription = null
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
private fun TopBarPreview() {
    JetPetTheme {
        Surface {
            TopBar(){}
        }
    }
}