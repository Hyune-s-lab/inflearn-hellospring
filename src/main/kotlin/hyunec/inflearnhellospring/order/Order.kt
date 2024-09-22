package hyunec.inflearnhellospring.order

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "orders")
class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(unique = true)
        val no: String,

        val total: BigDecimal
) {
    override fun toString(): String {
        return "Order(id=$id, no='$no', total=$total)"
    }
}
