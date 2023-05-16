package ra.model.entity;

import java.util.Date;

public class Order {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private long oderPay;
    private Date orderTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(int id, int userId, int productId, int quantity, long oderPay, Date orderTime, String status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.oderPay = oderPay;
        this.orderTime = orderTime;
        this.status = status;
    }

    private String status;

    public Order() {
    }

    public Order(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order(int id, int userId, int productId, int quantity, long oderPay, Date orderTime) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.oderPay = oderPay;
        this.orderTime = orderTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getOderPay() {
        return oderPay;
    }

    public void setOderPay(long oderPay) {
        this.oderPay = oderPay;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", oderPay=" + oderPay +
                ", orderTime=" + orderTime +
                '}';
    }
}
