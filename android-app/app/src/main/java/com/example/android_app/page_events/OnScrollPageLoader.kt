package com.example.android_app.page_events

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app.DI


class OnScrollPageLoader(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private val repository by lazy { DI.repository }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

        val isLoading = repository.isLoadingEvents.value
        val isLastPage = repository.allEventsLoaded.value
        if (
            !isLoading && !isLastPage &&
            visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
            firstVisibleItemPosition >= 0 &&
            totalItemCount >= PAGE_SIZE
        ) {
            repository.loadNextEventsPage()
        }
    }
}