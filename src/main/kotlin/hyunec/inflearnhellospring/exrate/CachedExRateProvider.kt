package hyunec.inflearnhellospring.exrate

import hyunec.inflearnhellospring.payment.ExRateProvider
import java.math.BigDecimal
import java.time.LocalDateTime


class CachedExRateProvider(
    private val target: ExRateProvider,
): ExRateProvider {

    private lateinit var cachedExRate: BigDecimal
    private lateinit var cacheExpiryTime: LocalDateTime

    init {
        refreshCache("USD");
    }

    override fun getExRate(currency: String): BigDecimal {
        if (cacheExpiryTime.isBefore(LocalDateTime.now())) {
            refreshCache(currency);
        }

        return cachedExRate;
    }

    private fun refreshCache(currency: String) {
        cachedExRate = this.target.getExRate(currency);
        cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

        println("### Cache Updated");
    }
}
