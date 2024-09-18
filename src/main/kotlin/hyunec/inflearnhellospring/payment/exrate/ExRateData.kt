package hyunec.inflearnhellospring.payment.exrate

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
@JvmRecord
data class ExRateData(
    val result: String,
    val rates: Map<String, BigDecimal>,
)
