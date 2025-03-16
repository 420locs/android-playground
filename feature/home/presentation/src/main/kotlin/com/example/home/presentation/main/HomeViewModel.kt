package com.example.home.presentation.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.example.home.domain.model.Song
import com.example.home.domain.useCase.GetAllRemoteSongs
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    val mediaPlayer: Player,
    private val getAllRemoteSongsUseCase: GetAllRemoteSongs,
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
            val allSongs = withContext(Dispatchers.IO) {
                getAllRemoteSongsUseCase()
            }
            todayMusics = Result.success(allSongs)
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
            val allSongs = withContext(Dispatchers.IO) {
                getAllRemoteSongsUseCase()
            }
            todayMusics = Result.success(allSongs)
            isRefreshLoading = false
        }
    }
}