package pet.com.jetpetrescue.domain.paginator

class PetPaginatorImpl<Page, Result>(
    private val initialKey: Page,
    private val loadingState: LoadingStateListener<Result>,
    private val onRequest: suspend (nextPage: Page) -> Result,
    private val getNextPage: (Result) -> Page
) : PetPaginator<Page, Result> {

    private var currentPage = initialKey

    override suspend fun fetchNextPage() {
        try {
            val result = onRequest.invoke(currentPage)
            loadingState.onLoadingStatechanged(true)
            currentPage = getNextPage.invoke(result)
            loadingState.onDataFeched(result)
            loadingState.onLoadingStatechanged(false)

        } catch (e: Exception) {
            loadingState.onLoadingStatechanged(false)
            loadingState.onError(e)
        }
    }

    override fun resetPage() {
        currentPage = initialKey
    }

}

interface LoadingStateListener<T> {
    fun onLoadingStatechanged(isLoading: Boolean)
    fun onDataFeched(data: T)
    fun onError(error: Throwable)
}