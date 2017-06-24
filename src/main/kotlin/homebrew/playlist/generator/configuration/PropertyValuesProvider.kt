package homebrew.playlist.generator.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

/**
 * Created by sargisazaryan on 6/24/17.
 */
@PropertySource("classpath:application-\${spring.profiles.active}.properties")
@Component
open class PropertyValuesProvider {

    @Value(value = "\${callback}")
    lateinit var url: String
}