package linKav20.github.com.entities.sender


import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

val link = "http://localhost:8000/pass"

fun sendMessage(passings: List<String>) {
    val email = SimpleEmail()
    email.hostName = "smtp.mail.ru"
    email.setSmtpPort(465)
    email.setAuthenticator(DefaultAuthenticator("dmasystem@mail.ru", "akRu5AhEDtpD0bxMM1Xe"))
    email.isSSLOnConnect = true
    email.setFrom("dmasystem@mail.ru")
    email.subject = "DMA: Оценка цифровой зрелости"
    email.setMsg("Пройдите тест, оценив компанию, в которой вы работаете, по ссылке $link")

    for (passing in passings) {
        email.addTo(passing)
    }

    email.send()
}