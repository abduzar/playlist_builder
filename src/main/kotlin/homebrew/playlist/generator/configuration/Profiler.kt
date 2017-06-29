package homebrew.playlist.generator.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

/**
 * Created by sargisazaryan on 6/29/17.
 */
@Configuration
class Profiler {
    @Autowired lateinit var env: Environment

    fun getActiveProfiles(): String {
        return env.activeProfiles[0]
    }
}