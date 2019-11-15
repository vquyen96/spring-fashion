package t1708m.fashion.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import net.bytebuddy.pool.TypePool;

import java.util.Set;

@Data
@Getter
@Setter
@Entity
public class Cloth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private double pricePerMeter; // giá theo mét.
    private String description;
    private String color;
    private String images;
    private int fabric;

    private long updatedAt;
    private long createdAt;
    private long deletedAt;
    private int status;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Account createdBy; // tạo bởi ai.

    @ManyToOne
    @JoinColumn(name = "updated_by_id")
    private Account updatedBy; // update bởi ai.

    @OneToMany(mappedBy = "cloth")
    private Set<Product> products;

    public enum Fabric {

        COTTON(1), CHIFFON(2), VOAN(3), REN(4), SILK(5);

        private int value;

        Fabric(int i) {
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
