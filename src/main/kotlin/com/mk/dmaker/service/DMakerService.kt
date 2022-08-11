package com.mk.dmaker.service

import com.mk.dmaker.code.StatusCode
import com.mk.dmaker.dto.CreateDeveloper
import com.mk.dmaker.dto.DeveloperDetailDto
import com.mk.dmaker.dto.DeveloperDto
import com.mk.dmaker.dto.EditDeveloper
import com.mk.dmaker.entity.Developer
import com.mk.dmaker.entity.RetiredDeveloper
import com.mk.dmaker.exception.DMakerErrorCode
import com.mk.dmaker.exception.DMakerException
import com.mk.dmaker.repository.DeveloperRepository
import com.mk.dmaker.repository.RetiredDeveloperRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class DMakerService(
    private val developerRepository: DeveloperRepository,
    private val retiredDeveloperRepository: RetiredDeveloperRepository,
    private val kafkaTemplate: KafkaTemplate<String, Developer>
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createDeveloper(request: CreateDeveloper.Request): CreateDeveloper.Response {
        validateCreateDeveloperRequest(request)

        val developer = createDeveloperFromRequest(request)
        developerRepository.save(developer)

        return CreateDeveloper.Response.fromEntity(developer)
    }

    private fun createDeveloperFromRequest(request: CreateDeveloper.Request): Developer {
        return Developer(
            memberId = request.memberId,
            developerLevel = request.developerLevel,
            developerSkillType = request.developerSkillType,
            experienceYears = request.experienceYears,
            statusCode = StatusCode.EMPLOYED,
            name = request.name,
            age = request.age
        )
    }

    @Transactional(readOnly = true)
    fun getAllEmployedDevelopers(): List<DeveloperDto> {
        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED).stream()
            .map(DeveloperDto::fromEntity)
            .collect(Collectors.toList())
    }

    /**
     *  to use Kafka produce
     */
    fun sendDeveloper(request: CreateDeveloper.Request) {
        validateCreateDeveloperRequest(request)

        val developer = createDeveloperFromRequest(request)
        log.info("mk-service: send contents: $developer")
        this.kafkaTemplate.send("mk-dmaker", developer)

    }

    /**
     * Business validation.
     */
    private fun validateCreateDeveloperRequest(request: CreateDeveloper.Request) {
        request.developerLevel.validateExperienceYears(request.experienceYears)

        developerRepository.findByMemberId(request.memberId)
            .ifPresent { throw DMakerException(DMakerErrorCode.DUPLICATED_MEMBER_ID) }
    }

    @Transactional(readOnly = true)
    fun getDeveloperDetail(memberId: String): DeveloperDetailDto {
        return DeveloperDetailDto.fromEntity(getDeveloperByMemberId(memberId))
    }

    private fun getDeveloperByMemberId(memberId: String) =
        developerRepository.findByMemberId(memberId).orElseThrow { DMakerException(DMakerErrorCode.NO_DEVELOPER) }

    @Transactional
    fun editDeveloper(memberId: String, request: EditDeveloper.Request): DeveloperDetailDto {
        request.developerLevel.validateExperienceYears(request.experienceYears)

        val developer = getDeveloperByMemberId(memberId)

        developer.apply {
            developerLevel = request.developerLevel
            developerSkillType = request.developerSkillType
            experienceYears = request.experienceYears
        }

        return DeveloperDetailDto.fromEntity(developer)
    }

    @Transactional
    fun deleteDeveloper(memberId: String): DeveloperDetailDto {
        // 1. EMPLOYED -> RETIRED
        val developer = getDeveloperByMemberId(memberId)
        developer.statusCode = StatusCode.RETIRED

        // 2. Save into RetiredDeveloper
        val retiredDeveloper = RetiredDeveloper(
            memberId = memberId,
            name = developer.name
        )

        retiredDeveloperRepository.save(retiredDeveloper)

        return DeveloperDetailDto.fromEntity(developer)
    }
}
