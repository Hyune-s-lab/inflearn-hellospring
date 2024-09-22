package hyunec.inflearnhellospring.order

import java.math.BigDecimal

class Order(
    val id: Long? = null,

    val no: String,

    val total: BigDecimal
) {
    override fun toString(): String {
        return "Order(id=$id, no='$no', total=$total)"
    }
}
