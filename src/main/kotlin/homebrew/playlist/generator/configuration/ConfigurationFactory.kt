package homebrew.playlist.generator.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

/**
 * Created by sargisazaryan on 6/29/17.
 */
@Configuration
class ConfigurationFactory {
    @Autowired
    lateinit var profiler: Profiler

    @Autowired
    lateinit var heroku: HerokuConfiguration

    @Autowired
    lateinit var localDev: LocalDevConfiguration

    fun currentCallback(): String {
        val profiles = profiler.getActiveProfiles()
        when (profiles[0].toString()) {
            "localdev" -> return localDev.callback
            "heroku" -> return heroku.callback
            else -> return "https://localhost:8080/callback"
        }
    }

}