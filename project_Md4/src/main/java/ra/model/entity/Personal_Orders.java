package ra.model.entity;

import java.util.Date;

public class Personal_Orders {
    private int id;
    private String userName ;
    private String userAddress ;
    private String userPhone;
    private String receiver;
    private String orderPhone;
    private String orderAddress;
    private Date oderDate;
    private int productId;
    private String productName;
    private int quantity;

    public Personal_Orders() {
    }

    public Personal_Orders(int id, String userName, String userAddress, String userPhone, String receiver, String orderPhone, String orderAddress, Date oderDate, int productId, String productName, int quantity) {
        this.id = id;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.receiver = receiver;
        this.orderPhone = orderPhone;
        this.orderAddress = orderAddress;
        this.oderDate = oderDate;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Date getOderDate() {
        return oderDate;
    }

    public void setOderDate(Date oderDate) {
        this.oderDate = oderDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Personal_Orders{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", receiver='" + receiver + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", oderDate=" + oderDate +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
