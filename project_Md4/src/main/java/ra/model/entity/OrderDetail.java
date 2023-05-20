package ra.model.entity;

public class OrderDetail {
    private int id;
    private int order_id;
    private int product_id;
    private float product_price;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int order_id, int product_id, float product_price, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_price = product_price;
        this.quantity = quantity;
    }

    public OrderDetail(int id, int order_id, int product_id, float product_price, int quantity) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_price = product_price;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", product_price=" + product_price +
                ", quantity=" + quantity +
                '}';
    }
}
