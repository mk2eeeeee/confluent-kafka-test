package com.mk.dmaker.dto

import com.mk.dmaker.entity.Developer
import com.mk.dmaker.type.DeveloperLevel
import com.mk.dmaker.type.DeveloperSkillType
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

class CreateDeveloper {

    data class Request(
        @NotNull val developerLevel: DeveloperLevel,
        @NotNull val developerSkillType: DeveloperSkillType,
        @NotNull @Min(0) @Max(20) val experienceYears: Int,

        @NotNull @Size(min = 3, max = 50, message = "memberId size must 3 ~ 50") val memberId: String,
        @NotNull @Size(min = 3, max = 20, message = "name size must 3 ~ 20") val name: String,
        @Min(18) val age: Int
    ) {
    }

    data class Response(
        val developerLevel: DeveloperLevel,
        val developerSkillType: DeveloperSkillType,
        val experienceYears: Int,
        val memberId: String
    ) {

        companion object {
            fun fromEntity(developer: Developer) = Response(
                memberId = developer.memberId,
                developerLevel = developer.developerLevel,
                developerSkillType = developer.developerSkillType,
                experienceYears = developer.experienceYears
            )
        }
    }
}
