package homebrew.playlist.generator.spotify.utils


import com.goebl.david.Webb
import com.wrapper.spotify.models.Playlist
import com.wrapper.spotify.models.PlaylistTrack
import com.wrapper.spotify.models.Track
import homebrew.playlist.generator.spotify.statics.SpotifyStatics
import homebrew.playlist.generator.spotify.statics.SpotifyStatics.api
import net.sf.json.JSONObject
import org.intellij.lang.annotations.Language
import java.util.*
import homebrew.playlist.generator.spotify.statics.SpotifyStatics.loggedInUser as user

/**
 * Created by sargisazaryan on 6/15/17.
 */
fun getPlaylistTracksByID(id: String, userID: String = user.id): MutableList<PlaylistTrack>? {
    val request = api.getPlaylistTracks(userID, id).build()
    return request.get().items
}

fun getPlaylistByID(id: String, userID: String = user.id): Playlist {
    val request = api.getPlaylist(userID, id).build()
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

fun getRandomTrackFromPlaylist(userID: String, playlistID: String): Track {
    val playlist = getPlaylistByID(id = playlistID, userID = userID)
    val tracks = playlist.tracks.items
    return tracks.getRandomItem().track
}

fun createNewPlayList(userID: String, playlistTitle: String, tracks: List<Track>) {
    val jsonString = "{\n  \"description\": \"Playlist created with application\",\n  \"public\": false,\n  \"name\": \"$playlistTitle\"\n}"
    val body: JSONObject = JSONObject.fromObject(jsonString)

    val uriList = tracks.map { "\"${it.uri}\"" }
    val tracksString = "{\"uris\": [${uriList.joinToString(",")}]}"

    val client = Webb.create()
    client.setDefaultHeader("Authorization", "Bearer ${SpotifyStatics.userToken}")
    client.setDefaultHeader("Accept", "application/json")

    val response = client.post("https://api.spotify.com/v1/users/$userID/playlists").body(body).asJsonObject().body
    val playlistID = response.get("id") as String


    client.post("https://api.spotify.com/v1/users/$userID/playlists/$playlistID/tracks").
            body(tracksString).
            ensureSuccess().asJsonObject().body
}

fun getIntegersFromString(string: String): List<Int> {
    return string.split(Regex("[^-\\d]+")).filter(String::isNotBlank).map(String::toInt)
}


fun String.getIntegers(): List<Int> {
    return this.split(Regex("[^-\\d]+")).filter(String::isNotBlank).map(String::toInt)
}

fun <T> List<T>.getRandomItem(): T {
    val random = Random()
    return this[random.nextInt(this.size)]
}