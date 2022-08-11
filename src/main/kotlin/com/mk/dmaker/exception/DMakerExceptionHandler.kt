package com.mk.dmaker.exception

import com.mk.dmaker.dto.DMakerErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class DMakerExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DMakerException::class)
    fun handleException(e: DMakerException, request: HttpServletRequest): DMakerErrorResponse {
        log.error("errorCode: ${e.dMakerErrorCode}, url: ${request.requestURI}, message: ${e.detailMessage}")

        return DMakerErrorResponse(
            errorCode = e.dMakerErrorCode,
            errorMessage = e.detailMessage
        )
    }

    @ExceptionHandler(value = [
        HttpRequestMethodNotSupportedException::class,
        MethodArgumentNotValidException::class
    ])
    fun handleBadRequest(e: Exception, request: HttpServletRequest): DMakerErrorResponse {
        log.error("url: ${request.requestURI}, message: ${e.message}")

        return DMakerErrorResponse(
            errorCode = DMakerErrorCode.INVALID_REQUEST,
            errorMessage = DMakerErrorCode.INVALID_REQUEST.message
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception, request: HttpServletRequest): DMakerErrorResponse {
        log.error("url: ${request.requestURI}, message: ${e.message}")

        return DMakerErrorResponse(
            errorCode = DMakerErrorCode.INTERNAL_SERVER_ERROR,
            errorMessage = DMakerErrorCode.INTERNAL_SERVER_ERROR.message
        )
    }
}
