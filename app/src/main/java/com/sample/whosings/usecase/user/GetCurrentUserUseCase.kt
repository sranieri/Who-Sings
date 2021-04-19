package com.sample.whosings.usecase.user

import com.sample.whosings.data.repository.UsersRepository
import com.sample.whosings.db.model.User
import com.sample.whosings.usecase.BaseUseCaseNoInput

/**
 * This usecase will retrieve the current user
 */
class GetCurrentUserUseCase(val repository: UsersRepository): BaseUseCaseNoInput<User?>() {
    override suspend fun execute(): User? {
        return repository.getCurrentUser()
    }
}