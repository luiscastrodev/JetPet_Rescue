package pet.com.jetpetrescue.presentation.navitagion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pet.com.jetpetrescue.presentation.detail.DetailScreen
import pet.com.jetpetrescue.presentation.home.Home
import pet.com.jetpetrescue.presentation.viewmodels.UiState

@Composable
fun JetPetNavigation(
    navController: NavHostController,
    uiState: UiState,
    onThemeChange: () -> Unit,
    onLoadNexPage: () -> Unit,
    onInfiniteScrollChange: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        composable(route = Screen.Home.name) {

            Home(
                onLoadNextPage = onLoadNexPage,
                onInfinitScollingChange = onInfiniteScrollChange,
                uiState = uiState,
                onPetClick = { index ->

                },
                onSwitchClick = onThemeChange
            )
        }

        composable(route = Screen.Detail.name) {
            DetailScreen(
                pet = uiState.animal.data?.get(0)!!
            ){

            }
        }
    }
}

enum class Screen {
    Home,
    Detail
}