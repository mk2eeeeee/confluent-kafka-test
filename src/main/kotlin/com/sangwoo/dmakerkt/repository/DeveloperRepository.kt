package com.sangwoo.dmakerkt.repository

import com.sangwoo.dmakerkt.code.StatusCode
import com.sangwoo.dmakerkt.entity.Developer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DeveloperRepository : JpaRepository<Developer, Long> {

    fun findByMemberId(memberId: String): Optional<Developer>

    fun findDevelopersByStatusCodeEquals(statusCode: StatusCode): List<Developer>
}