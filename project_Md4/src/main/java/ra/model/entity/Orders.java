package ra.model.entity;

import java.util.Date;

public class Orders {
    private int id;
    private int user_id;
    private Date createdDate;
    private boolean type;
    private String receiver;
    private int status;
    private String phone;
    private String address;

    public Orders() {
    }

    public Orders(int id, int user_id, Date createdDate, boolean type, String receiver, int status, String phone, String address) {
        this.id = id;
        this.user_id = user_id;
        this.createdDate = createdDate;
        this.type = type;
        this.receiver = receiver;
        this.status = status;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", createdDate=" + createdDate +
                ", type=" + type +
                ", receiver='" + receiver + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
