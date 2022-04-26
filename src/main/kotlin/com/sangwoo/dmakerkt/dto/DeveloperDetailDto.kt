package com.sangwoo.dmakerkt.dto

import com.sangwoo.dmakerkt.code.StatusCode
import com.sangwoo.dmakerkt.entity.Developer
import com.sangwoo.dmakerkt.type.DeveloperLevel
import com.sangwoo.dmakerkt.type.DeveloperSkillType

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
