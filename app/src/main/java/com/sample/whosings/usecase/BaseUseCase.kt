package com.sample.whosings.usecase

abstract class BaseUseCase<Input, Output> {

    abstract suspend fun execute(params: Input) : Output

}

abstract class BaseUseCaseNoInput<Output> {

    abstract suspend fun execute() : Output

}
