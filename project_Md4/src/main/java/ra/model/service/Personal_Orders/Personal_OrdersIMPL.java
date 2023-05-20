package ra.model.service.Personal_Orders;

import ra.model.entity.Personal_Orders;
import ra.model.entity.Product;
import ra.model.service.OrderDetail.IOrderDetail_Service;
import ra.model.service.OrderDetail.OrderDetail_IMPL;
import ra.model.service.orders.IOrdersService;
import ra.model.service.orders.OrdersIMPL;
import ra.model.until.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Personal_OrdersIMPL implements IPersonal_OrdersService{
    IOrderDetail_Service  orderDetailService=new OrderDetail_IMPL();
    IOrdersService ordersService=new OrdersIMPL();

    @Override
    public List<Personal_Orders> findAll() {
        List<Personal_Orders> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_ORDERS_And_detail_findAll()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Personal_Orders personalOrders = new Personal_Orders();
                personalOrders.setId(rs.getInt("id"));
                personalOrders.setUserName(rs.getString("UserName"));
                personalOrders.setUserAddress(rs.getString("UserAddress"));
                personalOrders.setUserPhone(rs.getString("userPhone"));
                personalOrders.setReceiver(rs.getString("receiver"));
                personalOrders.setOrderPhone(rs.getString("orderPhone"));
                personalOrders.setOrderAddress(rs.getString("orderAddress"));
                personalOrders.setOderDate(rs.getDate("OderDate"));
                personalOrders.setProductId(rs.getInt("productId"));
                personalOrders.setProductName(rs.getString("productName"));
                personalOrders.setQuantity(rs.getInt("quantity"));
                list.add(personalOrders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean save(Personal_Orders personalOrders) {

        return true;
    }

    @Override
    public boolean update(Personal_Orders personalOrders) {
        return false;
    }

    @Override
    public Personal_Orders findById(int id) {
        Personal_Orders personalOrders = new Personal_Orders();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_ORDERS_And_detail_findAll()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                personalOrders.setId(rs.getInt("id"));
                personalOrders.setUserName(rs.getString("UserName"));
                personalOrders.setUserAddress(rs.getString("UserAddress"));
                personalOrders.setUserPhone(rs.getString("userPhone"));
                personalOrders.setReceiver(rs.getString("receiver"));
                personalOrders.setOrderPhone(rs.getString("orderPhone"));
                personalOrders.setOrderAddress(rs.getString("orderAddress"));
                personalOrders.setOderDate(rs.getDate("OderDate"));
                personalOrders.setProductId(rs.getInt("productId"));
                personalOrders.setProductName(rs.getString("productName"));
                personalOrders.setQuantity(rs.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return personalOrders;
    }

    @Override
    public boolean delete(int e) {
        return false;
    }
}
