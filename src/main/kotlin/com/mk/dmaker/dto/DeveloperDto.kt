package com.mk.dmaker.dto

import com.mk.dmaker.entity.Developer
import com.mk.dmaker.type.DeveloperLevel
import com.mk.dmaker.type.DeveloperSkillType

data class DeveloperDto(
    val developerLevel: DeveloperLevel,
    val developerSkillType: DeveloperSkillType,
    val memberId: String
) {

    companion object {
        fun fromEntity(developer: Developer) = DeveloperDto(
            memberId = developer.memberId,
            developerLevel = developer.developerLevel,
            developerSkillType = developer.developerSkillType
        )
    }
}
