package ra.model.entity;

public class Product {
private  int id;
private String productName;
private int categoryId;
private float price;
private  int stoke;
private String title;
private String img;
private String description1;
private String description2;
private String description3;
private String description4;
private String description5;

    public Product() {
     }

    public Product(String productName, int categoryId, long price, int stoke) {
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.stoke = stoke;
    }

    public Product(String productName, int categoryId, long price, int stoke, String title, String img) {
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.stoke = stoke;
        this.title = title;
        this.img = img;
    }

    public Product(int id, String productName, int categoryId, long price, int stoke, String title, String img, String description1, String description2, String description3, String description4, String description5) {
        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.stoke = stoke;
        this.title = title;
        this.img = img;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.description4 = description4;
        this.description5 = description5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStoke() {
        return stoke;
    }

    public void setStoke(int stoke) {
        this.stoke = stoke;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getDescription4() {
        return description4;
    }

    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    public String getDescription5() {
        return description5;
    }

    public void setDescription5(String description5) {
        this.description5 = description5;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", stoke=" + stoke +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", description1='" + description1 + '\'' +
                ", description2='" + description2 + '\'' +
                ", description3='" + description3 + '\'' +
                ", description4='" + description4 + '\'' +
                ", description5='" + description5 + '\'' +
                '}';
    }
}
