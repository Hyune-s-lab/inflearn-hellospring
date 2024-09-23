package hyunec.inflearnhellospring.data

import hyunec.inflearnhellospring.order.Order
import hyunec.inflearnhellospring.order.OrderRepository
import jakarta.annotation.PostConstruct
import org.springframework.jdbc.core.simple.JdbcClient
import javax.sql.DataSource


class JdbcOrderRepository(
    private val dataSource: DataSource,
) : OrderRepository {
    private val jdbcClient = JdbcClient.create(dataSource)

    @PostConstruct
    fun initDb() {
        jdbcClient.sql(
            """
            create table orders (id bigint not null, no varchar(255), total numeric(38,2), primary key (id));
            alter table if exists orders drop constraint if exists UK_43egxxciqr9ncgmxbdx2avi8n;
            alter table if exists orders add constraint UK_43egxxciqr9ncgmxbdx2avi8n unique (no);
            create sequence orders_SEQ start with 1 increment by 50;
        """.trimIndent()
        ).update()
    }

    override fun save(order: Order) {
        val id = jdbcClient.sql("select next value for orders_SEQ")
            .query(Long::class.java)
            .single()
        order.id = id

        jdbcClient.sql("insert into orders (no,total,id) values (?,?,?)")
            .params(order.no, order.total, order.id)
            .update()
    }
}
