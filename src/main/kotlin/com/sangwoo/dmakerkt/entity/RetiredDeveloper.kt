package com.sangwoo.dmakerkt.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class RetiredDeveloper(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    val memberId: String,
    val name: String,

    @CreatedDate var createdAt: LocalDateTime = LocalDateTime.MIN,
    @LastModifiedDate var updatedAt: LocalDateTime = LocalDateTime.MIN
) {
}