package homebrew.playlist.generator.spotify.domain

import homebrew.playlist.generator.spotify.statics.SpotifyStatics

/**
 * Created by sargisazaryan on 7/5/17.
 */
data class PlaylistMapping(val hrZone: String, val playlistID: String, val playlistOwnerID: String) {
    companion object {
        fun getPlaylistPairs(): List<Pair<String, String>> {
            val pairs = mutableListOf<Pair<String, String>>()
            SpotifyStatics.zoneMapping.forEach {
                val pair = Pair(first = it.playlistID, second = it.playlistOwnerID)
                pairs.add(pair)
            }
            return pairs
        }
    }
}