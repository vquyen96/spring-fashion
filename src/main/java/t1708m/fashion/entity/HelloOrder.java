package t1708m.fashion.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Để tên là Order sẽ gây lỗi khi mapping vào database (từ khoá đặc biệt.)
 * Có thể fix bằng cách thêm @Table với name khác. Tuy nhiên trong trường hợp này đổi thành HelloOrder cho mới mẻ.
 * */
@Data
@Getter
@Setter
@Entity
public class HelloOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private double totalPrice;
    private String shipName;
    private String shipPhone;
    private String shipEmail;
    private String shipAddress;

    private long preferAt;
    private long deliveryAt;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    /**
     * Order này được tạo bởi người dùng nào. Một người dùng có thể tạo nhiều order.
     * */
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Account createdBy;

    /**
     * Order này được update bởi Admin nào. Một admin có thể update nhiều order.
     * */
    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private Account updatedBy;

    /**
     * Một order có thể có nhiều order detail, đây là danh sách order detail của order này.
     * Thể hiện trong order này có những sản phẩm nào với số lượng bao nhiêu.
     * */
    @OneToMany(mappedBy = "order")
    private Set<HelloOrderDetail> orderDetails;

    public enum Status {

        SUCCESS(1), CONFIRMED(2), WAIT_TO_CONFIRM(3), CANCELED(4);

        private int value;

        Status(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public HelloOrder() {
    }
}
