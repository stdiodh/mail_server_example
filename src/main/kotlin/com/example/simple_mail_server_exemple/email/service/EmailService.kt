package com.example.simple_mail_server_exemple.email.service

import com.example.simple_mail_server_exemple.email.dto.EmailRequestDto
import com.example.simple_mail_server_exemple.email.dto.EmailResponseDto
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class EmailService(
    private val mailSender: JavaMailSender,
    private val templateEngine : TemplateEngine
){
    //이메일 전송
    fun sendEmail(emailRequestDto: EmailRequestDto) : EmailResponseDto
    {
        val to = emailRequestDto.email
        val message = getHtmlMailMessage(to)
        mailSender.send(message)
        return EmailResponseDto( message = "성공" )
    }

    private fun getMailMessage(email : String) : SimpleMailMessage {
        val message = SimpleMailMessage()
        message.subject = "테스트 이메일"
        message.text = "테스트 중입니다."
        message.setTo(email)
        return message
    }

    private fun getHtmlMailMessage(email: String) : MimeMessage {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        val context = Context()
        context.setVariable("message", "테스트 이메일")
        val htmlContent : String = templateEngine.process("EmailTemplate",context)

        helper.setSubject("테스트 이메일")
        helper.setText(htmlContent, true)
        helper.setTo(email)

        return message
    }
}