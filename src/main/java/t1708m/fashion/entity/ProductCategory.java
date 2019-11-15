package t1708m.fashion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String image;
    private String description;

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

}
