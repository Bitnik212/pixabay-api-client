package moe.nikolay.nwcode

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationScrollListener {
    /**
     * Pagination class to add more items to the list when reach the last item.
     */
    abstract class Adapter
    /**
     * Supporting only LinearLayoutManager for now.
     *
     * @param layoutManager
     */
        (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

//        abstract fun isLastPage(): Boolean
//
//        abstract fun isLoading(): Boolean
        private val TAG = "PaginationScrollListener"
        var loadMore = false
        var count = 0
        private var trigger1 = false

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
//            if (!isLoading() && !isLastPage()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (totalItemCount > count) {
                        loadMore = false
                        count = totalItemCount
                    }
                    checkLoadMoreItems(lastVisiblePosition, totalItemCount)
                }
                if (firstVisibleItemPosition == 0 && !trigger1) {
                    isFirstElement()
                    trigger1 = true
                }
                if (firstVisibleItemPosition != 0) trigger1 = false

//            }
        }

        private fun checkLoadMoreItems(lastVisiblePosition: Int, itemsCount: Int = 10) {
            if (itemsCount > 0) if (lastVisiblePosition == itemsCount - 1) {
                if (!loadMore) {
                    loadMore = true
                    loadMoreItems()
                }
            }
        }

        abstract fun loadMoreItems()
        abstract fun isFirstElement()

    }

}