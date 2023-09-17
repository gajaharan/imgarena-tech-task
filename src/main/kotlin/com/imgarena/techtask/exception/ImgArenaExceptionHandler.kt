package com.imgarena.techtask.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ImgExceptionHandler {
    @ExceptionHandler(ConverterNotFoundException::class)
    fun handleDataNotFoundException(
        ex: ConverterNotFoundException
    ): ResponseEntity<String> =
        ResponseEntity.badRequest().body(ex.message)

    @ExceptionHandler(MappingException::class)
    fun handleMappingException(
        ex: MappingException
    ): ResponseEntity<String> =
        ResponseEntity.badRequest().body(ex.message)

    @ExceptionHandler(MissingRequestHeaderException::class)
    fun handleMissingRequestHeaderException(
        ex: MissingRequestHeaderException
    ): ResponseEntity<String> =
        ResponseEntity.badRequest().body(ex.message)
}