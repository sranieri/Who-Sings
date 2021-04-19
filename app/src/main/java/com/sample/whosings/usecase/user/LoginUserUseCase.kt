package com.sample.whosings.usecase.user

import com.sample.whosings.data.repository.UsersRepository
import com.sample.whosings.usecase.BaseUseCase

/**
 * This usecase will login a user into the game,
 * if the user isn't already into the database it will be added
 */
class LoginUserUseCase(val repository: UsersRepository) : BaseUseCase<String, Boolean>() {
    override suspend fun execute(params: String): Boolean {
        repository.saveUser(params)
        return true
    }
}