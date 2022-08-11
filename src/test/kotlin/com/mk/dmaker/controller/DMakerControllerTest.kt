package com.mk.dmaker.controller

import com.ninjasquad.springmockk.MockkBean
import com.mk.dmaker.dto.DeveloperDto
import com.mk.dmaker.service.DMakerService
import com.mk.dmaker.type.DeveloperLevel
import com.mk.dmaker.type.DeveloperSkillType
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.hamcrest.CoreMatchers.`is`
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.nio.charset.StandardCharsets

@SpringBootTest
@AutoConfigureMockMvc
internal class DMakerControllerTest(
    val mockMvc: MockMvc,
    @MockkBean val dMakerService: DMakerService
) : DescribeSpec({

    extensions(SpringExtension)

    val requestContentType = MediaType(
        MediaType.APPLICATION_JSON.type,
        MediaType.APPLICATION_JSON.subtype,
        StandardCharsets.UTF_8
    )

    describe("DMakerController") {
        context("When get all developers") {
            it("Should return developers") {
                val juniorDto = DeveloperDto(
                    developerSkillType = DeveloperSkillType.BACK_END,
                    developerLevel = DeveloperLevel.JUNIOR,
                    memberId = "memberId"
                )

                val seniorDto = DeveloperDto(
                    developerSkillType = DeveloperSkillType.FRONT_END,
                    developerLevel = DeveloperLevel.SENIOR,
                    memberId = "memberId2"
                )

                every { dMakerService.getAllEmployedDevelopers() } returns listOf(juniorDto, seniorDto)

                mockMvc.get("/developers") {
                    contentType = requestContentType
                }.andExpect {
                    status { isOk() }
                }.andDo {
                    print()
                }.andExpect {
                    jsonPath("$.[0].developerSkillType", `is`(DeveloperSkillType.BACK_END.name))
                    jsonPath("$.[0].developerLevel", `is`(DeveloperLevel.JUNIOR.name))
                    jsonPath("$.[1].developerSkillType", `is`(DeveloperSkillType.FRONT_END.name))
                    jsonPath("$.[1].developerLevel", `is`(DeveloperLevel.SENIOR.name))
                }
            }
        }
    }
})
