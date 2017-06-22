package homebrew.playlist.generator.spotify.utils

import org.hamcrest.core.Is
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test

/**
 * Created by sargisazaryan on 6/22/17.
 */
internal class UtilityFunctionsKtTest {
    @Test
    fun getPlaylistFromUrl() {
        val input = "https://api.spotify.com/v1/users/1150327512/playlists/5cRe0ykgz0qmIXd1dXHNaZ"
        assertThat("Wrong value", getPlaylistID(input), Is.`is`("5cRe0ykgz0qmIXd1dXHNaZ"))
    }

}