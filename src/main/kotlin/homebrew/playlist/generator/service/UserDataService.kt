package homebrew.playlist.generator.service

import homebrew.playlist.generator.repo.UserRepo
import homebrew.playlist.generator.spotify.domain.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by sargisazaryan on 7/1/17.
 */
@Service
class UserDataService : DataService<UserEntity> {

    @Autowired
    lateinit var userRepo: UserRepo

    override fun saveOrUpdate(entity: UserEntity): UserEntity {
        val search = userRepo.findAll().filter { it.userDatabaseID == entity.userDatabaseID }
        when {
            search.isEmpty() -> {
                return userRepo.save(entity)
            }
            search.size == 1 -> {
                return userUpdate(entity)
            }
            else -> {
                throw IllegalArgumentException("Something goes wrong on user update")
            }
        }
    }

    override fun delete(id: Long) {
        userRepo.delete(id)
    }

    override val fullList: List<UserEntity>
        get() = userRepo.findAll().toList()

    override fun getObjectByID(id: Long): UserEntity {
        return userRepo.findOne(id)
    }

    private fun userUpdate(entity: UserEntity): UserEntity {
        userRepo.delete(entity.userDatabaseID)
        return userRepo.save(entity)
    }
}