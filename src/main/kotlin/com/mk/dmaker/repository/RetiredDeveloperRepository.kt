package com.mk.dmaker.repository

import com.mk.dmaker.entity.RetiredDeveloper
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RetiredDeveloperRepository : JpaRepository<RetiredDeveloper, Long> {
}
