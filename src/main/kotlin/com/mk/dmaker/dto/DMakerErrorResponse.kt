package com.mk.dmaker.dto

import com.mk.dmaker.exception.DMakerErrorCode

data class DMakerErrorResponse(
    val errorCode: DMakerErrorCode,
    val errorMessage: String
) {
}
