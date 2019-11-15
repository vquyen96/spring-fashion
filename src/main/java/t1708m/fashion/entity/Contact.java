package t1708m.fashion.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;

    private long createdAt;
    private long updateAt;
    private long deletedAt;
    private int status;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Account createdBy; // tạo bởi ai.

    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private Account updatedBy; // update bởi ai.

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

}
