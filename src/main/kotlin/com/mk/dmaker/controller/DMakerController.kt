package com.mk.dmaker.controller

import com.mk.dmaker.dto.CreateDeveloper
import com.mk.dmaker.dto.DeveloperDetailDto
import com.mk.dmaker.dto.DeveloperDto
import com.mk.dmaker.dto.EditDeveloper
import com.mk.dmaker.service.DMakerService
import com.mk.dmaker.type.DeveloperLevel
import com.mk.dmaker.type.DeveloperSkillType
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class DMakerController(
    private val dMakerService: DMakerService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/developers")
    fun getAllDevelopers(): List<DeveloperDto> {
        log.info("GET /developers HTTP/1.1")

        return dMakerService.getAllEmployedDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    fun getDeveloperDetail(@PathVariable memberId: String): DeveloperDetailDto {
        log.info("GET /developer/$memberId HTTP/1.1")

        return dMakerService.getDeveloperDetail(memberId)
    }

    @PostMapping("/create-developer")
    fun createDeveloper(@Valid @RequestBody request: CreateDeveloper.Request): CreateDeveloper.Response {
        log.info("POST /create-developer HTTP/1.1")

        return dMakerService.createDeveloper(request)
    }

    @PutMapping("/developer/{memberId}")
    fun editDeveloper(
        @PathVariable memberId: String,
        @Valid @RequestBody request: EditDeveloper.Request
    ): DeveloperDetailDto {
        log.info("PUT /developer/$memberId HTTP/1.1")

        return dMakerService.editDeveloper(memberId, request)
    }

    @DeleteMapping("/developer/{memberId}")
    fun deleteDeveloper(@PathVariable memberId: String): DeveloperDetailDto {
        log.info("DELETE /developer/$memberId HTTP/1.1")

        return dMakerService.deleteDeveloper(memberId)
    }
}
