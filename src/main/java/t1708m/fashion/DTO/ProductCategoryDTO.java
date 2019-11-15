package t1708m.fashion.DTO;

import t1708m.fashion.entity.ProductCategory;
import t1708m.fashion.util.ObjectUtil;

public class ProductCategoryDTO {
    private int id;
    private int productId;
    private String name;
    private String image;
    private String gender;
    private String price;
    private long updatedAt;
    private long updatedBy;
    private long createdAt;
    private long createdBy;
    private long deletedAt;
    private int status;

    public ProductCategoryDTO() {
        }
    public ProductCategoryDTO(int id, String name) {
                this.id = id;
                this.name = name;
            }
    public ProductCategoryDTO(ProductCategory category) {
                    this.image = "";
                    this.gender = "";
                    this.price = "";
        ObjectUtil.cloneObject(this, category);
//        this.createdAt = Long.parseLong(DateTimeUtil.formatDateFromLong(category.getCreatedAt()));
//        this.updatedAt = Long.parseLong(DateTimeUtil.formatDateFromLong(category.getUpdatedAt()));
//        this.deletedAt = Long.parseLong(DateTimeUtil.formatDateFromLong(category.getDeletedAt()));
//        this.status = Integer.parseInt(category.getStatus() == 1 ? "Active" : "Deactive");

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
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
