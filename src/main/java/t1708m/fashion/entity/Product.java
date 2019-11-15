package t1708m.fashion.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String description;
    private String photos;
    private int size;
    private int gender;
    private int accountType; // mẫu này được tạo bởi người dùng hay shop.
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Account createdBy; // tạo bởi ai.

    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private Account updatedBy; // update bởi ai.

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category; // kiểu dáng áo dài nào?

    @ManyToOne
    @JoinColumn(name = "cloth_id")
    private Cloth cloth; // dùng mảnh vải nào ?

    public Product(String name, BigDecimal price, int quantity, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Product() {

    }


    public enum Gender {

        MALE(1), FEMALE(0);

        private int value;

        Gender(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public enum AccountType {

        MEMBER(1), SHOP(2);

        private int value;

        AccountType(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public enum Size {

        XS(1), S(2), M(3), L(4), XL(5), XXL(6);

        private int value;

        Size(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public Product(String name, BigDecimal price, int quantity, String description, String photos, int size,
                   int gender, int accountType, long createdAt, long updatedAt, long deletedAt, int status, Account createdBy,
                   Account updatedBy, ProductCategory category, Cloth cloth) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.photos = photos;
        this.size = size;
        this.gender = gender;
        this.accountType = accountType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.category = category;
        this.cloth = cloth;
    }
}
