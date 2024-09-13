package com.example.sample.presentation.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.domain.model.Post
import com.example.sample.domain.usecase.GetListPost
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SampleViewModel(
    private val getListPostUseCase: GetListPost
) : ViewModel() {

    var isFirstLoadLoading: Boolean by mutableStateOf(false)
        private set
    var isRefreshLoading: Boolean by mutableStateOf(false)
        private set
    var listData: Result<List<Post>>? by mutableStateOf(null)
        private set

    fun initLoadData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
            Log.d("NinhTBM", "CoroutineExceptionHandler got $exception with context $context")
            listData = Result.failure(exception)
            isFirstLoadLoading = false
        }

        if (isFirstLoadLoading || isRefreshLoading) return

        isFirstLoadLoading = true
        viewModelScope.launch(coroutineExceptionHandler) {
            val listPosts = getListPostUseCase()
            listData = Result.success(listPosts)
            isFirstLoadLoading = false
        }
    }
    fun refreshData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
            Log.d("NinhTBM", "CoroutineExceptionHandler got $exception with context $context")
            listData = Result.failure(exception)
            isRefreshLoading = false
        }

        if (isFirstLoadLoading || isRefreshLoading) return

        isRefreshLoading = true
        viewModelScope.launch(coroutineExceptionHandler) {
            val listPosts = getListPostUseCase()
            listData = Result.success(listPosts)
            isRefreshLoading = false
        }
    }
}