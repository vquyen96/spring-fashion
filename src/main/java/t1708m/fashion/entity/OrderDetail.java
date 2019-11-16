package t1708m.fashion.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

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

    @Column(name = "order_id", insertable = false, updatable = false)
    private long orderId;

    @Column(name = "product_id", insertable = false, updatable = false)
    private long productId;

    private int quantity;
    private double unitPrice;

    /**
     * Trả lời câu hỏi order detail này thuộc order nào. Trường order_id để
     * insertable = false, updatable = false (bắt buộc) để có thể dùng được ở trong class OrderDetailId.
     */
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    /**
     * Trả lời câu hỏi order detail này của sản phẩm nào.
     * Trường order_id để insertable = false, updatable = false (bắt buộc) để có thể
     * dùng được ở trong class OrderDetailId.
     */
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    public OrderDetail(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
}
