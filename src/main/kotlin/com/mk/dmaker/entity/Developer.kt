package com.mk.dmaker.entity

import com.mk.dmaker.code.StatusCode
import com.mk.dmaker.type.DeveloperLevel
import com.mk.dmaker.type.DeveloperSkillType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Developer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Enumerated(EnumType.STRING) var developerLevel: DeveloperLevel,
    @Enumerated(EnumType.STRING) var developerSkillType: DeveloperSkillType,

    var experienceYears: Int,
    val memberId: String,
    val name: String,
    val age: Int,

    @Enumerated(EnumType.STRING) var statusCode: StatusCode,

    @CreatedDate var createdAt: LocalDateTime = LocalDateTime.MIN,
    @LastModifiedDate var updatedAt: LocalDateTime = LocalDateTime.MIN
) {
}
