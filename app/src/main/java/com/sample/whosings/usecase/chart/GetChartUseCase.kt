package com.sample.whosings.usecase.chart

import com.sample.whosings.data.repository.UsersRepository
import com.sample.whosings.db.model.User
import com.sample.whosings.usecase.BaseUseCaseNoInput

/**
 * This usecase will retrieve the users list sorted by score
 */
class GetChartUseCase(val repository: UsersRepository) : BaseUseCaseNoInput<List<User>>() {
    override suspend fun execute(): List<User> {
        return repository.getUsers().sortedByDescending {
            it.score
        }
    }
}