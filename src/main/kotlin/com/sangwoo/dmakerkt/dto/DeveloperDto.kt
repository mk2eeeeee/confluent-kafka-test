package com.sangwoo.dmakerkt.dto

import com.sangwoo.dmakerkt.entity.Developer
import com.sangwoo.dmakerkt.type.DeveloperLevel
import com.sangwoo.dmakerkt.type.DeveloperSkillType

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
