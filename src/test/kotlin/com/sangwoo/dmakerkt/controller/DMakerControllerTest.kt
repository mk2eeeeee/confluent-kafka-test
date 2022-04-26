package com.sangwoo.dmakerkt.controller

import com.sangwoo.dmakerkt.dto.DeveloperDto
import com.sangwoo.dmakerkt.service.DMakerService
import com.sangwoo.dmakerkt.type.DeveloperLevel
import com.sangwoo.dmakerkt.type.DeveloperSkillType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.charset.StandardCharsets

@WebMvcTest
@AutoConfigureMockMvc
internal class DMakerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var dMakerService: DMakerService

    private val contentType = MediaType(
        MediaType.APPLICATION_JSON.type,
        MediaType.APPLICATION_JSON.subtype,
        StandardCharsets.UTF_8
    )

    @Test
    fun getAllDevelopers() {
        // given
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

        given(dMakerService.getAllEmployedDevelopers())
            .willReturn(listOf(juniorDto, seniorDto))

        // when
        // then
        mockMvc.perform(get("/developers"))//.contentType(contentType))
            .andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.[0].developerSkillType", `is`(DeveloperSkillType.BACK_END.name)))
            .andExpect(jsonPath("$.[0].developerLevel", `is`(DeveloperLevel.JUNIOR.name)))
            .andExpect(jsonPath("$.[1].developerSkillType", `is`(DeveloperSkillType.FRONT_END.name)))
            .andExpect(jsonPath("$.[1].developerLevel", `is`(DeveloperLevel.SENIOR.name)))
    }
}
