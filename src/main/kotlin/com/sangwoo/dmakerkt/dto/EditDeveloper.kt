package com.sangwoo.dmakerkt.dto

import com.sangwoo.dmakerkt.type.DeveloperLevel
import com.sangwoo.dmakerkt.type.DeveloperSkillType
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class EditDeveloper {

    data class Request(
        @NotNull val developerLevel: DeveloperLevel,
        @NotNull val developerSkillType: DeveloperSkillType,
        @NotNull @Min(0) @Max(20) val experienceYears: Int,
    ) {
    }
}