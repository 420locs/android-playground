package com.example.home.data

import com.example.home.data.entity.MusicResponse
import com.example.home.data.entity.SongResponse
import com.example.home.domain.HomeRepository
import com.example.home.domain.model.Song
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode

class HomeInternalRepository(private val googleSampleMusicService: GoogleSampleMusicService) : HomeRepository {

    override suspend fun getListSongs(): List<Song> {
        val response = googleSampleMusicService.getAllSongs()

        when (response.status) {
            HttpStatusCode.OK -> {
                val musicsResponse = checkNotNull(response.body<MusicResponse?>())
                val musics = checkNotNull(musicsResponse.listMusic)
                return musics.map(::mapResponseToSongModel)
            }
            else -> throw IllegalStateException("catalog.json api failed!")
        }
    }

    private fun mapResponseToSongModel(target: SongResponse): Song {
        return Song(
            id = target.id,
            title = target.title,
            album = target.album,
            artist = target.artist,
            genre = target.genre,
            source = target.source,
            image = target.image,
            trackNumber = target.trackNumber,
            totalTrackCount = target.totalTrackCount,
            duration = target.duration,
            site = target.site,
        )
    }

}