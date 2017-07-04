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
        return userRepo.save(entity)
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