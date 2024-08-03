package com.example.simple_mail_server_exemple.email.controller

import com.example.simple_mail_server_exemple.email.dto.EmailRequestDto
import com.example.simple_mail_server_exemple.email.dto.EmailResponseDto
import com.example.simple_mail_server_exemple.email.service.EmailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailController (
    private val emailService: EmailService
) {
    //테스트 이메일 전송
    @PostMapping("/api/mail/test")
    private fun sendEmail (@RequestBody emailRequestDto: EmailRequestDto) : ResponseEntity<EmailResponseDto> {
        val result = emailService.sendEmail(emailRequestDto)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}