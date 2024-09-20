package hyunec.inflearnhellospring.exrate

import com.fasterxml.jackson.databind.ObjectMapper
import hyunec.inflearnhellospring.payment.ExRateProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URI
import java.util.stream.Collectors

class WebApiExRateProvider: ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/${currency}"
        return runApiForExRate(url)
    }

    private fun runApiForExRate(url: String): BigDecimal {
        val uri = URI(url)
        val response = executeApi(uri)
        return parseExRate(response)
    }

    private fun parseExRate(response: String): BigDecimal {
        val mapper = ObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"]!!
        return exRate
    }

    private fun executeApi(uri: URI): String {
        val connection = uri.toURL().openConnection() as HttpURLConnection
        val br = BufferedReader(InputStreamReader(connection.inputStream))
        val response = br.lines().collect(Collectors.joining("\n"))
        br.close()
        return response
    }
}
