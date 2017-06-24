package homebrew.playlist.generator.spotify.utils

import com.wrapper.spotify.models.Playlist
import com.wrapper.spotify.models.PlaylistTrack
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics.api
import org.intellij.lang.annotations.Language

/**
 * Created by sargisazaryan on 6/15/17.
 */
fun getPlaylistTracksByID(id: String): MutableList<PlaylistTrack>? {
    val request = api.getPlaylistTracks(SpotifyStatics.loggedInUser.id, id).build()
    return request.get().items
}

fun getPlaylistByID(id: String): Playlist {
    val request = api.getPlaylist(SpotifyStatics.loggedInUser.id, id).build()
    return request.get()
}

/**
 * Url example
 * https://api.spotify.com/v1/users/1150327512/playlists/5cRe0ykgz0qmIXd1dXHNaZ
 */
fun getPlaylistID(url: String): String? {
    @Language("RegExp")
    val regex = "^.*/playlists/(.*)$".toRegex()
    return regex.matchEntire(url)?.groups?.get(1)?.value
}

fun getUserFromURL(url: String): String? {
    @Language("RegExp")
    val regex = "^.*/users/(.*)/playlists/.*$".toRegex()
    return regex.matchEntire(url)?.groups?.get(1)?.value
}