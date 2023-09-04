package pet.com.jetpetrescue.presentation.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.data.DummyPetDataSource
import hoods.com.jetpetrescue.ui.theme.JetPetTheme
import pet.com.jetpetrescue.presentation.components.PetInfoItem
import pet.com.jetpetrescue.presentation.components.TopBar
import pet.com.jetpetrescue.presentation.viewmodels.UiState
import pet.com.jetpetrescue.utils.ResourceHolder

@Composable
fun Home(
    onSwitchClick: () -> Unit,
    onPetClick: (Int) -> Unit,
    onLoadNextPage: () -> Unit,
    onInfinitScollingChange: (Boolean) -> Unit,
    uiState: UiState
) {
    //val petList = DummyPetDataSource.dogList.shuffled()

    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar {
                onSwitchClick()
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            when (uiState.animal) {
                is ResourceHolder.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }

                is ResourceHolder.Success -> {
                    val petList = uiState.animal.data ?: emptyList()

                    itemsIndexed(petList) { index, item ->
                        PetInfoItem(pet = item, onPetItemClick = {
                            onPetClick.invoke(index)
                        }
                        )
                        LaunchedEffect(key1 = scrollState) {
                            if (
                                index >= petList.lastIndex &&
                                !uiState.isFetchingPet &&
                                uiState.startInfiniteScrolling
                            ) {
                                onLoadNextPage()
                            }
                        }
                    }

                    if (uiState.isFetchingPet) {
                        item {
                            CircularProgressIndicator()
                        }
                    }

                    item {
                        AnimatedVisibility(visible = uiState.loadingMoreButtonVisible) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                TextButton(onClick = {
                                    onLoadNextPage.invoke()
                                    onInfinitScollingChange(true)
                                }) {
                                    Text(text = "Load More Pets")
                                }
                            }
                        }
                    }
                }

                else -> {
                    uiState.animal.throwable?.printStackTrace()
                    item {
                        Text(
                            text = "Error Occured",
                            color = MaterialTheme.colors.error
                        )
                    }
                }
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
            //Home({}, {},{},false)
        }
    }
}

