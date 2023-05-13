package ra.model.entity;

public class Order {
    private int OrderId;
    private int userId;
    private int productId;
    private int quantity;
    private long oderPay;

    public Order() {
    }

    public Order(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order(int OrderId, int userId, int productId, int quantity) {
        this.OrderId = OrderId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
//        this.oderPay = oderPay;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        this.OrderId = orderId;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + OrderId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", oderPay=" + oderPay +
                '}';
    }
}
