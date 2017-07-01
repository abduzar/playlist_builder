package homebrew.playlist.generator.service

/**
 * Created by sargisazaryan on 7/1/17.
 */
interface DataService<T> {
    val fullList: List<T>
    fun getObjectByID(id: Long): T
    fun saveOrUpdate(entity: T): T
    fun delete(id: Long)
}