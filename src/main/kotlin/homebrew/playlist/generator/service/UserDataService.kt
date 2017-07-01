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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        userRepo.delete(id)
    }

    override val fullList: List<UserEntity>
        get() = userRepo.findAll().toList()

    override fun getObjectByID(id: Long): UserEntity {
        return userRepo.findOne(id)
    }
}