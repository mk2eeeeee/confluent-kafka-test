package com.mk.dmaker.repository

import com.mk.dmaker.code.StatusCode
import com.mk.dmaker.entity.Developer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DeveloperRepository : JpaRepository<Developer, Long> {

    fun findByMemberId(memberId: String): Optional<Developer>

    fun findDevelopersByStatusCodeEquals(statusCode: StatusCode): List<Developer>
}
