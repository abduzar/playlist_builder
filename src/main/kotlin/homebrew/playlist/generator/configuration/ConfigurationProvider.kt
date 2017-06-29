package homebrew.playlist.generator.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

/**
 * Created by sargisazaryan on 6/29/17.
 */
@Configuration
class ConfigurationProvider {
    @Autowired
    lateinit var env: Environment

    @Autowired
    lateinit var local: LocalDevConfiguration

    @Autowired
    lateinit var heroku: HerokuConfiguration

    fun getConfig(): Config {
        val profiles = env.activeProfiles
        when {
            profiles.size > 1 -> return local
            profiles[0].toString() == "localdev" -> return local
            profiles[0].toString() == "heroku" -> return heroku
            else -> return local
        }
    }
}