package homebrew.playlist.generator.controller

import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by sargisazaryan on 4/15/17.
 */
@Controller
class ErrorHandler : ErrorController {
    @RequestMapping(value = "/error")
    fun error(): String {
        return "coming_soon"
    }

    override fun getErrorPath(): String {
        return "/error"
    }
}