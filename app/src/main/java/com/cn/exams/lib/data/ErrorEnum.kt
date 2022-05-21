package com.cn.exams.lib.data

import android.content.res.Resources
import com.cn.exams.R

enum class ErrorEnum {
    UNKNOWN_ERROR,

    // user error
    MISSING_USERNAME,
    INVALID_USERNAME_LENGTH,
    MISSING_PASSWORD,
    INVALID_PASSWORD_LENGTH,
    MISSING_USER_NAME,
    INVALID_USER_NAME_LENGTH,
    MISSING_BIRTH,
    MISSING_TEL,
    EXIST_USERNAME,
    WRONG_USERNAME_PASSWORD,
    REQUIRED_LOGIN,
    NOT_FOUND_USER,
    PERMISSION_REQUIRED,

    // bank error
    MISSING_BANK_NAME,
    INVALID_BANK_NAME_LENGTH,
    MISSING_BANK_SCOPE,
    INVALID_BANK_SCOPE,
    NOT_FOUND_BANK,
    MISSING_BANK_ID,

    // question error
    MISSING_QUESTION_CONTENT,
    MISSING_CORRECT_ANSWER,
    INVALID_CORRECT_ANSWER,
    MISSING_ANSWER,
    MISSING_QUESTION_ID,
    NOT_FOUND_QUESTION,

    // answer error
    MISSING_ANSWER_CONTENT,

    // contest error
    NOT_FOUND_CONTEST,
    MISSING_CONTEST_NAME,
    INVALID_CONTEST_NAME_LENGTH,
    MISSING_CONTEST_QUANTITY,
    INVALID_CONTEST_QUANTITY,
    MISSING_CONTEST_SCOPE,
    MISSING_CONTEST_START_AT,
    MISSING_CONTEST_BANK,
    MISSING_CONTEST_ID,
    MISSING_CONTEST_CODE,
    WRONG_CODE_PASSWORD,
    NOT_IN_JOIN_TIME,
    NOT_ENOUGH_QUESTION,
    JOIN_CONTEST_FIRST,

    // from android
    LOCAL_ERROR,
    INVALID_BIRTH_FORMAT,
    INVALID_CF_PASSWORD
}

fun ErrorEnum.message(resources: Resources): String {
    return when (this) {
        ErrorEnum.UNKNOWN_ERROR -> resources.getString(R.string.unknown_error)
        ErrorEnum.MISSING_USERNAME -> resources.getString(R.string.missing_username)
        ErrorEnum.INVALID_USERNAME_LENGTH -> resources.getString(R.string.invalid_username_length)
        ErrorEnum.MISSING_PASSWORD -> resources.getString(R.string.missing_password)
        ErrorEnum.INVALID_PASSWORD_LENGTH -> resources.getString(R.string.invalid_password_length)
        ErrorEnum.MISSING_USER_NAME -> resources.getString(R.string.missing_user_name)
        ErrorEnum.INVALID_USER_NAME_LENGTH -> resources.getString(R.string.invalid_user_name_length)
        ErrorEnum.MISSING_BIRTH -> resources.getString(R.string.missing_birth)
        ErrorEnum.MISSING_TEL -> resources.getString(R.string.missing_tel)
        ErrorEnum.EXIST_USERNAME -> resources.getString(R.string.exist_username)
        ErrorEnum.WRONG_USERNAME_PASSWORD -> resources.getString(R.string.wrong_username_password)
        ErrorEnum.REQUIRED_LOGIN -> this.name
        ErrorEnum.NOT_FOUND_USER -> this.name
        ErrorEnum.PERMISSION_REQUIRED -> this.name
        ErrorEnum.MISSING_BANK_NAME -> resources.getString(R.string.missing_bank_name)
        ErrorEnum.INVALID_BANK_NAME_LENGTH -> resources.getString(R.string.invalid_bank_name_length)
        ErrorEnum.MISSING_BANK_SCOPE -> this.name
        ErrorEnum.INVALID_BANK_SCOPE -> this.name
        ErrorEnum.NOT_FOUND_BANK -> this.name
        ErrorEnum.MISSING_BANK_ID -> this.name
        ErrorEnum.MISSING_QUESTION_CONTENT -> this.name
        ErrorEnum.MISSING_CORRECT_ANSWER -> this.name
        ErrorEnum.INVALID_CORRECT_ANSWER -> this.name
        ErrorEnum.MISSING_ANSWER -> this.name
        ErrorEnum.MISSING_QUESTION_ID -> this.name
        ErrorEnum.NOT_FOUND_QUESTION -> this.name
        ErrorEnum.MISSING_ANSWER_CONTENT -> this.name
        ErrorEnum.NOT_FOUND_CONTEST -> this.name
        ErrorEnum.MISSING_CONTEST_NAME -> this.name
        ErrorEnum.INVALID_CONTEST_NAME_LENGTH -> this.name
        ErrorEnum.MISSING_CONTEST_QUANTITY -> this.name
        ErrorEnum.INVALID_CONTEST_QUANTITY -> this.name
        ErrorEnum.MISSING_CONTEST_SCOPE -> this.name
        ErrorEnum.MISSING_CONTEST_START_AT -> this.name
        ErrorEnum.MISSING_CONTEST_BANK -> this.name
        ErrorEnum.MISSING_CONTEST_ID -> this.name
        ErrorEnum.MISSING_CONTEST_CODE -> this.name
        ErrorEnum.WRONG_CODE_PASSWORD -> this.name
        ErrorEnum.NOT_IN_JOIN_TIME -> this.name
        ErrorEnum.NOT_ENOUGH_QUESTION -> this.name
        ErrorEnum.JOIN_CONTEST_FIRST -> this.name
        ErrorEnum.LOCAL_ERROR -> resources.getString(R.string.unknown_error)
        ErrorEnum.INVALID_BIRTH_FORMAT -> resources.getString(R.string.invalid_birth_format)
        ErrorEnum.INVALID_CF_PASSWORD -> resources.getString(R.string.invalid_cf_password)
//        else -> resources.getString(R.string.unknown_error)
    }
}