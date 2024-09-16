package hyunec.inflearnhellospring.payment

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.util.stream.Collectors

class PaymentService {
    fun prepare(orderId: Long, currency: String, foreignCurrencyAmount: BigDecimal): Payment { // 환율 가져오기
        // 환율 가져오기
        val url = URL("https://open.er-api.com/v6/latest/${currency}")
        val connection = url.openConnection() as HttpURLConnection
        val br = BufferedReader(InputStreamReader(connection.inputStream))
        val response = br.lines().collect(Collectors.joining("\n"))
        br.close()

        val mapper = ObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"]!!
        println(exRate)

        // 금액 계산
        val convertedAmount = foreignCurrencyAmount.multiply(exRate)

        // 유효 시간 계산
        val validUntil = LocalDateTime.now().plusMinutes(30)

        return Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil)
    }
}

fun main(args: Array<String>) {
    val paymentService = PaymentService()
    val payment = paymentService.prepare(100, "USD", BigDecimal(50.7))
    println(payment)
}
