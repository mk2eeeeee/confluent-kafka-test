package com.mk.dmaker.exception

enum class DMakerErrorCode(
    val message: String
) {
    NO_DEVELOPER("No developer."),
    DUPLICATED_MEMBER_ID("MemberId is duplicated."),
    LEVEL_EXPERIENCE_YEARS_NOT_MATCHED("EXP and YEARS not matched."),

    INTERNAL_SERVER_ERROR("Internal server error."),
    INVALID_REQUEST("Invalid request.")
}
