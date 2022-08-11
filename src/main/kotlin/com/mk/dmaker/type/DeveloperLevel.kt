package com.mk.dmaker.type

import com.mk.dmaker.constant.DMakerConstant
import com.mk.dmaker.exception.DMakerErrorCode
import com.mk.dmaker.exception.DMakerException

enum class DeveloperLevel(
    private val description: String,
    private val experienceYearsValidator: (Int) -> Boolean
) {
    NEW("신입 개발자", { it == 0 }),
    JUNIOR("주니어 개발자", { it >= 1 && it <= DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS }),
    JUNGNIOR("중니어 개발자", { it > DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS && it < DMakerConstant.MIN_SENIOR_EXPERIENCE_YEARS }),
    SENIOR("시니어 개발자", { it >= DMakerConstant.MIN_SENIOR_EXPERIENCE_YEARS });

    fun validateExperienceYears(years: Int) {
        if (!experienceYearsValidator(years)) {
            throw DMakerException(DMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED)
        }
    }
}
