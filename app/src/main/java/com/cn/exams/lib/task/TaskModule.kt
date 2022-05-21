package com.cn.exams.lib.task

class TaskModule(
    private val taskName: MutableList<Int>,
    private val onSuccess: () -> Unit
) {

    fun markAsSuccess(name: Int) {
        taskName.remove(name)
        checkSuccess()
    }

    private fun checkSuccess() {
        if (taskName.isEmpty()) {
            onSuccess()
        }
    }
}