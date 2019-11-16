package t1708m.fashion.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int role;

    private long updatedAt;
    private long createdAt;
    private long deletedAt;
    private int status;

//    /**
//     * Tài khoản có thể bị update thông tin bởi admin. Trường này trả lời tài khoản update bởi ai.
//     */
//    @OneToOne
//    @JoinColumn(name = "updated_by")
//    private Account updatedBy;
//
//    /**
//     * Tài khoản của người dùng có thể tạo nhiều order, trường này thể hiện những order của người dùng này.
//     */
//    @OneToMany(mappedBy = "createdBy")
//    private Set<Order> createdOrders;
//
//    /**
//     * Tài khoản admin có thể update trạng thái order, chuyển các trạng thái thành công, huỷ... Trường này
//     * thể hiện danh sách những order được update bởi tài khoản admin này.
//     * Trường này chỉ tồn tại nếu là admin hệ thống. Role là 99.
//     */
//    @OneToMany(mappedBy = "updatedBy")
//    private Set<Order> updatedOrders;

    public enum Role {

        CUSTOMER(1), ADMIN(99);

        private int value;

        Role(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public enum Status {

        ACTIVE(1), DEACTIVE(0), DELETED(-1);

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

    public Account(String username, String email, int role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Account() {
    }
}