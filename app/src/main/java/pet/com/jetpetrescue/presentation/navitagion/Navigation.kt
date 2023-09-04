package pet.com.jetpetrescue.presentation.navitagion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                    navController.navigate(route = "${Screen.Detail.name}/${index}")
                },
                onSwitchClick = onThemeChange
            )
        }

        composable(
            route = "${Screen.Detail.name}/{id}",
            arguments = listOf(navArgument("id")
            {
                type = NavType.IntType
                defaultValue = 0
            })
        ) { backstackEntry ->
            val id = backstackEntry.arguments!!.getInt("id")
            DetailScreen(
                pet = uiState.animal.data?.get(id)!!
            ) {
                navController.navigate(Screen.Home.name){
                    popUpTo(route = Screen.Home.name){
                        inclusive = true
                    }
                }
            }
        }
    }
}

enum class Screen {
    Home,
    Detail
}