package com.sample.whosings.usecase.user

import com.sample.whosings.data.repository.UsersRepository
import com.sample.whosings.usecase.BaseUseCaseNoInput

/**
 * This usecase will logout the current user, the user won't be deleted from the database
 */
class LogoutUserUseCase(val repository: UsersRepository): BaseUseCaseNoInput<Boolean>() {
    override suspend fun execute(): Boolean{
        repository.logoutUser()
        return true
    }
}