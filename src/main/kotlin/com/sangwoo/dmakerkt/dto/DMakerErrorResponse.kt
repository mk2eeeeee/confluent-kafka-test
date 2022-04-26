package com.sangwoo.dmakerkt.dto

import com.sangwoo.dmakerkt.exception.DMakerErrorCode

data class DMakerErrorResponse(
    val errorCode: DMakerErrorCode,
    val errorMessage: String
) {
}