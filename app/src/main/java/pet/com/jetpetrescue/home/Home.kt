package pet.com.jetpetrescue.home
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import hoods.com.jetpetrescue.data.DummyPetDataSource
import pet.com.jetpetrescue.components.PetInfoItem

@Composable
fun Home() {
    val petList = DummyPetDataSource.dogList.shuffled()

    LazyColumn {
        items(petList) { pet ->
            PetInfoItem(pet, onPetItemClick = {})
        }
    }
}
