package homebrew.playlist.generator.controller

import homebrew.playlist.generator.spotify.connector.SpotifyConnector
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics.api
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by sargisazaryan on 3/15/17.
 */
@Controller
class PageController {
    val connector = SpotifyConnector()

    @RequestMapping(value = "/")
    fun mainPage():String{
        return "index"
    }

    @RequestMapping(value = "/login", method = arrayOf(RequestMethod.GET))
    fun spotifyLogin(): String {
        connector.connect()
        return "redirect:${connector.makeAuthorizationURL(api)}"
    }

    @RequestMapping(value = "/callback", method = arrayOf(RequestMethod.GET))
    fun spotifyLoginCallback(@RequestParam("code") code: String): String {
        SpotifyStatics.callBackCode = code
        connector.getUser(code)
        return "redirect:/user"
    }

    @RequestMapping(value = "/user")
    fun comingSoon(modelMap: ModelMap): String {
        modelMap.put("user", SpotifyStatics.loggedInUser)
        val playlists = api.getPlaylistsForUser(SpotifyStatics.loggedInUser.id).build()
        modelMap.put("playlists", playlists.get().items)
        return "user_details"
    }

}