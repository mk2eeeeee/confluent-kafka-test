package com.sangwoo.dmakerkt.repository

import com.sangwoo.dmakerkt.entity.RetiredDeveloper
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RetiredDeveloperRepository : JpaRepository<RetiredDeveloper, Long> {
}