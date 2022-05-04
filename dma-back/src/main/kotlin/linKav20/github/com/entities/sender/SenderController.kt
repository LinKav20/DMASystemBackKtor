package linKav20.github.com.entities.sender


import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.HtmlEmail

val link = "http://localhost:8000/test"
val message = "\"<!DOCTYPE html>" +
        "<html>" +
        "<head>" +
        "<meta charset=utf-8>" +
        "</head>" +
        "<body>" +
        "<h1>Оценка цифровой зрелости</h1>" +
        "<p>Пройдите тест, оценив компанию, " +
        "в которой вы работаете, по " +
        "<a href=\\\"$link\\\"> " +
        "ссылке </a>" +
        "</p>" +
        "</body" +
        "></html>\""


fun sendMessage(passings: List<String>) {
    val email = HtmlEmail()
    email.hostName = "smtp.mail.ru"
    email.setSmtpPort(465)
    email.setAuthenticator(DefaultAuthenticator("dmasystem@mail.ru", "akRu5AhEDtpD0bxMM1Xe"))
    email.isSSLOnConnect = true
    email.setFrom("dmasystem@mail.ru")
    email.subject = "DMA: Оценка цифровой зрелости"
    email.setCharset("UTF-8")
    email.setHtmlMsg(message)
    email.setTextMsg("Пройдите тест, оценив компанию, в которой вы работаете, по ссылке $link")

    for (passing in passings) {
        email.addTo(passing)
    }

    email.send()
}