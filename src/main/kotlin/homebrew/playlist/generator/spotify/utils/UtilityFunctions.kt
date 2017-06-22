package homebrew.playlist.generator.spotify.utils

import com.wrapper.spotify.models.SimplePlaylist
import org.intellij.lang.annotations.Language

/**
 * Created by sargisazaryan on 6/15/17.
 */

/**
 * Url example
 * https://api.spotify.com/v1/users/1150327512/playlists/5cRe0ykgz0qmIXd1dXHNaZ
 */
fun getPlaylistID(url: String): String? {
    @Language("RegExp")
    val regex = "^.*/playlists/(.*)$".toRegex()
    return regex.matchEntire(url)?.groups?.get(1)?.value
}

fun getPlaylistIDList(list: List<SimplePlaylist>): List<String> {
    val idList = list.map { getPlaylistID(it.href)!! }
    return idList
}