package hyunec.inflearnhellospring.payment.exrate

import com.fasterxml.jackson.databind.ObjectMapper
import hyunec.inflearnhellospring.payment.payment.ExRateProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = URL("https://open.er-api.com/v6/latest/${currency}")
        val connection = url.openConnection() as HttpURLConnection
        val br = BufferedReader(InputStreamReader(connection.inputStream))
        val response = br.lines().collect(Collectors.joining("\n"))
        br.close()

        val mapper = ObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"]!!

        println("### API ExRate: $exRate")

        return exRate
    }
}
