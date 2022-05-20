package com.cn.exams.lib.data

class ResponseObject<T>(
    private val block: (LoadedAction<T>) -> Unit
) {

    private var successBlock: (T) -> Unit = { }
    private var failureBlock: (ErrorResponseModel) -> Unit = { }

    fun onSuccess(successBlock: (T) -> Unit): ResponseObject<T> {
        this.successBlock = successBlock
        return this
    }

    fun onFailure(failureBlock: (ErrorResponseModel) -> Unit): ResponseObject<T> {
        this.failureBlock = failureBlock
        return this
    }

    fun call() {
        block(LoadedAction(successBlock, failureBlock))
    }
}