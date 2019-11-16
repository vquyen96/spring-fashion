package t1708m.fashion.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Để tên là Order sẽ gây lỗi khi mapping vào database (từ khoá đặc biệt.)
 * Có thể fix bằng cách thêm @Table với name khác. Tuy nhiên trong trường hợp này đổi thành HelloOrder cho mới mẻ.
 * */
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Account account;


    /**
     * Một order có thể có nhiều order detail, đây là danh sách order detail của order này.
     * Thể hiện trong order này có những sản phẩm nào với số lượng bao nhiêu.
     * */
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

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

    public Order() {
    }

    public Order(String shipName, String shipAddress, Account account) {
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.account = account;
        this.status = Status.CONFIRMED.getValue();
    }
}
