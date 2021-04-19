package com.sample.whosings.usecase.user

import com.sample.whosings.data.repository.UsersRepository
import com.sample.whosings.usecase.BaseUseCase

/**
 * This usecase will update the user score
 */
class UpdateUserScoreUseCase(val repository: UsersRepository) : BaseUseCase<Int, Boolean>() {
    override suspend fun execute(params: Int): Boolean {
        repository.updateCurrentUserScore(params)
        return true
    }
}