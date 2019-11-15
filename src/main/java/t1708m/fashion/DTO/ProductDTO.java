package t1708m.fashion.DTO;

import t1708m.fashion.entity.Product;
import t1708m.fashion.util.ObjectUtil;

public class ProductDTO {
    private int id;
    private int categoryId;
    private String name;
    private String price;
    private String description;
    private String image;
    private String size;
    private String color;
    private long updatedAt;
    private long createdAt;
    private long deletedAt;
    private int status;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public ProductDTO(Product product) {
        this.price = "";
        this.description = "";
        this.image = "";
        this.size = "";
        this.color = "";
        ObjectUtil.cloneObject(this, product);
//        this.createdAt = Long.parseLong(DateTimeUtil.formatDateFromLong(product.getCreatedAt()));
//        this.updatedAt = Long.parseLong(DateTimeUtil.formatDateFromLong(product.getUpdatedAt()));
//        this.deletedAt = Long.parseLong(DateTimeUtil.formatDateFromLong(product.getDeletedAt()));
//        this.status = Integer.parseInt(product.getStatus() == 1 ? "Active" : "Deactive");

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
