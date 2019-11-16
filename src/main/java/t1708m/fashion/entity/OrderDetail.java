package t1708m.fashion.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Ăn theo HelloOrder, đặt là HelloOrderDetail cho đồng bộ.
 */
@Getter
@Setter
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;
    private BigDecimal unitPrice;

    /**
     * Trả lời câu hỏi order detail này thuộc order nào. Trường order_id để
     * insertable = false, updatable = false (bắt buộc) để có thể dùng được ở trong class OrderDetailId.
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * Trả lời câu hỏi order detail này của sản phẩm nào.
     * Trường order_id để insertable = false, updatable = false (bắt buộc) để có thể
     * dùng được ở trong class OrderDetailId.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderDetail(int quantity, Product product, Order order, BigDecimal unitPrice) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
        this.unitPrice = unitPrice;
    }
}
