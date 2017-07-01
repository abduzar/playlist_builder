package homebrew.playlist.generator.repo

import homebrew.playlist.generator.spotify.domain.UserEntity
import org.springframework.data.repository.CrudRepository

/**
 * Created by sargisazaryan on 7/1/17.
 */
interface UserRepo : CrudRepository<UserEntity, Long>