package com.mk.dmaker.dto

import com.mk.dmaker.code.StatusCode
import com.mk.dmaker.entity.Developer
import com.mk.dmaker.type.DeveloperLevel
import com.mk.dmaker.type.DeveloperSkillType

data class DeveloperDetailDto(
    val developerLevel: DeveloperLevel,
    val developerSkillType: DeveloperSkillType,

    val experienceYears: Int,
    val memberId: String,
    val statusCode: StatusCode,
    val name: String,
    val age: Int
) {

    companion object {
        fun fromEntity(developer: Developer) = DeveloperDetailDto(
            developerLevel = developer.developerLevel,
            developerSkillType = developer.developerSkillType,
            experienceYears = developer.experienceYears,
            memberId = developer.memberId,
            statusCode = developer.statusCode,
            name = developer.name,
            age = developer.age
        )
    }
}
