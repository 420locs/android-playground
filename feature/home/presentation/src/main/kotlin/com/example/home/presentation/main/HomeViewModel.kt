package com.example.home.presentation.main

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.music.presentation.media.GetAllAudioFile
import com.example.music.presentation.media.MediaPlayerHandler
import com.example.music.presentation.media.Song
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllAudioFileUseCase: GetAllAudioFile,
    private val mediaPlayerHandler: MediaPlayerHandler,
) : ViewModel() {

    var isFirstLoadLoading: Boolean by mutableStateOf(false)
        private set
    var isRefreshLoading: Boolean by mutableStateOf(false)
        private set
    var todayMusics: Result<List<Song>>? by mutableStateOf(null)
        private set

    fun initLoadData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
            Log.d("NinhTBM", "CoroutineExceptionHandler got $exception with context $context")
            todayMusics = Result.failure(exception)
            isFirstLoadLoading = false
        }

        if (isFirstLoadLoading || isRefreshLoading) return

        isFirstLoadLoading = true
        viewModelScope.launch(coroutineExceptionHandler) {
//            val listPosts = getListPostUseCase()
//            listData = Result.success(listPosts)

            val content = getAllAudioFileUseCase()
            todayMusics = Result.success(content)
            isFirstLoadLoading = false
        }
    }

    fun refreshData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
            Log.d("NinhTBM", "CoroutineExceptionHandler got $exception with context $context")
            todayMusics = Result.failure(exception)
            isRefreshLoading = false
        }

        if (isFirstLoadLoading || isRefreshLoading) return

        isRefreshLoading = true
        viewModelScope.launch(coroutineExceptionHandler) {
//            val listPosts = getListPostUseCase()
//            listData = Result.success(listPosts)
            isRefreshLoading = false
        }
    }
}