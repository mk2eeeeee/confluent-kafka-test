package com.mk.dmaker.exception

class DMakerException(
    val dMakerErrorCode: DMakerErrorCode,
    val detailMessage: String = dMakerErrorCode.message
) : RuntimeException(detailMessage) {
}
