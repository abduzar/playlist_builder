package homebrew.playlist.generator.repo

import org.springframework.data.repository.CrudRepository

/**
 * Created by sargisazaryan on 7/1/17.
 */
interface CommonRepo<T> : CrudRepository<T, Long>