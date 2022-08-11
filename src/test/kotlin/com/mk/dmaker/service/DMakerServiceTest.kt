package com.mk.dmaker.service

import com.mk.dmaker.code.StatusCode
import com.mk.dmaker.dto.CreateDeveloper
import com.mk.dmaker.entity.Developer
import com.mk.dmaker.exception.DMakerErrorCode
import com.mk.dmaker.exception.DMakerException
import com.mk.dmaker.repository.DeveloperRepository
import com.mk.dmaker.repository.RetiredDeveloperRepository
import com.mk.dmaker.type.DeveloperLevel
import com.mk.dmaker.type.DeveloperSkillType
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import java.util.*

internal class DMakerServiceTest : DescribeSpec({

    val developerRepository = mockk<DeveloperRepository>()
    val retiredDeveloperRepository = mockk<RetiredDeveloperRepository>()
    val dMakerService = DMakerService(developerRepository, retiredDeveloperRepository)

    val defaultDeveloper = Developer(
        developerLevel = DeveloperLevel.SENIOR,
        developerSkillType = DeveloperSkillType.FRONT_END,
        experienceYears = 12,
        memberId = "memberId",
        statusCode = StatusCode.EMPLOYED,
        name = "name",
        age = 32
    )

    val defaultCreateRequest = CreateDeveloper.Request(
        developerLevel = DeveloperLevel.SENIOR,
        developerSkillType = DeveloperSkillType.FRONT_END,
        experienceYears = 12,
        memberId = "memberId",
        name = "name",
        age = 32
    )

    describe("Create developer") {
        context("When save default developer") {
            it("Should return default developer") {
                val developerSlot = slot<Developer>()

                every { developerRepository.findByMemberId(any()) } returns Optional.empty()
                every { developerRepository.save(capture(developerSlot)) } returns defaultDeveloper

                dMakerService.createDeveloper(defaultCreateRequest)

                verify(exactly = 1) { developerRepository.save(any()) }

                val savedDeveloper = developerSlot.captured
                savedDeveloper.developerLevel shouldBe DeveloperLevel.SENIOR
                savedDeveloper.developerSkillType shouldBe DeveloperSkillType.FRONT_END
                savedDeveloper.experienceYears shouldBe 12
            }
        }

        context("When save developer with duplicated id") {
            it("Should fail with duplicated error") {
                every { developerRepository.findByMemberId(any()) } returns Optional.of(defaultDeveloper)

                shouldThrowExactly<DMakerException> { dMakerService.createDeveloper(defaultCreateRequest) }
                    .dMakerErrorCode shouldBe DMakerErrorCode.DUPLICATED_MEMBER_ID
            }
        }
    }
})
