package com.sangwoo.dmakerkt.entity

import com.sangwoo.dmakerkt.code.StatusCode
import com.sangwoo.dmakerkt.type.DeveloperLevel
import com.sangwoo.dmakerkt.type.DeveloperSkillType
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
