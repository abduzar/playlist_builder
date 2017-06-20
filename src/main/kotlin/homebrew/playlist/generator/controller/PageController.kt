package homebrew.playlist.generator.controller

import homebrew.playlist.generator.spotify.connector.SpotifyConnector
import homebrew.playlist.generator.spotify.connector.statics.SpotifyStatics.api
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by sargisazaryan on 3/15/17.
 */
@Controller
class PageController {

    @RequestMapping(value = "/")
    fun mainPage():String{
        return "index"
    }

    @RequestMapping(value = "/login", method = arrayOf(RequestMethod.GET))
    fun spotifyLogin(): String {
        val connector = SpotifyConnector()
        connector.connect()
        return "redirect:${connector.makeAuthorizationURL(api)}"
    }

    @RequestMapping(value = "/callback", method = arrayOf(RequestMethod.GET))
    fun spotifyLoginCallback(@RequestParam("code") code: String): String {
        val authRequest = api.authorizationCodeGrant(code).build()
        return "redirect:/"
    }

    @RequestMapping(value = "/about_us")
    fun comingSoon(): String {
        return "about_us"
    }

}