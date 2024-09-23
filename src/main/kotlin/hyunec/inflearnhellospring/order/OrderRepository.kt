package hyunec.inflearnhellospring.order

interface OrderRepository {
    fun save(order: Order)
}
