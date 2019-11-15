package t1708m.fashion.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Getter
@Setter
@Embeddable
public class OrderDetailId implements Serializable {

    @Column(name = "order_id", insertable = false, updatable = false)
    private long orderId;
    @Column(name = "product_id", insertable = false, updatable = false)
    private long productId;

    public OrderDetailId(long orderId, long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
