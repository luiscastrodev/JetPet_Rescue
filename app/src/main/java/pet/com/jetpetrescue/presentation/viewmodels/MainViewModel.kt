package pet.com.jetpetrescue.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pet.com.jetpetrescue.Graph
import pet.com.jetpetrescue.domain.models.Pet
import pet.com.jetpetrescue.domain.paginator.LoadingStateListener
import pet.com.jetpetrescue.domain.paginator.PetPaginatorImpl
import pet.com.jetpetrescue.domain.repository.PetRepository
import pet.com.jetpetrescue.utils.ResourceHolder

class MainViewModel(
    private val repository: PetRepository = Graph.petRepository
) : ViewModel(), LoadingStateListener<ResourceHolder<List<Pet>>> {

    override fun onLoadingStatechanged(isLoading: Boolean) {
        uiState = uiState.copy(isFetchingPet = isLoading)
    }

    override fun onDataFeched(data: ResourceHolder<List<Pet>>) {
        uiState = uiState.updateAnimal(data)
    }

    override fun onError(error: Throwable) {
        Log.d(TAG, "onError: Feching Pet error:", error)
    }

    private fun UiState.updateAnimal(newData: ResourceHolder<List<Pet>>): UiState {
        return when (newData) {
            is ResourceHolder.Error -> {
                copy(animal = newData)
            }

            is ResourceHolder.Success -> {
                val updateData = this.animal.data?.combineData(newData.data!!) ?: newData
                copy(animal = updateData)
            }

            else -> {
                this
            }
        }
    }

    private fun <Data> List<Data>.combineData(newList: List<Data>): ResourceHolder<List<Data>> {
        return ResourceHolder.Success(data = this.union(newList).toList())
    }

    var uiState by mutableStateOf(UiState())

    companion object {
        const val TAG = "MainViewModel"
    }

    private var petPaginator: PetPaginatorImpl<Int, ResourceHolder<List<Pet>>>

    init {
        petPaginator = PetPaginatorImpl(
            initialKey = getPage(uiState.animal.data),
            loadingState = this,
            onRequest = { page ->
                if (uiState.isFetchingPet) return@PetPaginatorImpl ResourceHolder.Loading()
                val pet = fechAnimals(page)
                pet
            },
            getNextPage = { resourceHolder ->
                getPage(resourceHolder.data)
            }
        )
        loadingNexPage()
    }


    fun loadingNexPage() {
        viewModelScope.launch {
            petPaginator.fetchNextPage()
        }
    }

    private suspend fun fechAnimals(page: Int): ResourceHolder<List<Pet>> {
        return repository.getAnimal(page)
    }

    fun onInfiniteScollchange(start: Boolean) {
        uiState = uiState.copy(
            startInfiniteScrolling = start,
            loadingMoreButtonVisible = !start
        )
    }

    private fun getPage(pageSource: List<Pet>?): Int {
        return if (pageSource?.isNotEmpty() == true) {
            pageSource[pageSource.lastIndex].currentPage + 1
        } else {
            1
        }
    }
}


data class UiState(
    val animal: ResourceHolder<List<Pet>> = ResourceHolder.Loading(),
    val isFetchingPet: Boolean = false,
    val loadingMoreButtonVisible: Boolean = true,
    val startInfiniteScrolling: Boolean = false
)
