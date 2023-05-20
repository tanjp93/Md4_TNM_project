package ra.model.service.orders;

import ra.model.entity.Orders;
import ra.model.until.ConnectionToDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersIMPL implements IOrdersService<Orders> {
    @Override
    public List<Orders> findAll() {
        List<Orders> listOrders = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call  PROC_ORDERS_findALL()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setId(rs.getInt("id"));
                orders.setUser_id(rs.getInt("user_id"));
                orders.setCreatedDate(rs.getDate("createdDate"));
                orders.setType(rs.getBoolean("type"));
                orders.setReceiver(rs.getString("receiver"));
                orders.setStatus(rs.getInt("status"));
                orders.setPhone(rs.getString("phone"));
                orders.setAddress(rs.getString("address"));
                listOrders.add(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return listOrders;
    }

    @Override
    public boolean save(Orders orders) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call  PROC_ORDERS_add(?,?,?,?,?,?,?)");
            callSt.setInt(1, orders.getUser_id());
            callSt.setDate(2, (Date) orders.getCreatedDate());
            callSt.setBoolean(3, orders.getType());
            callSt.setString(4, orders.getReceiver());
            callSt.setInt(5, orders.getStatus());
            callSt.setString(6, orders.getPhone());
            callSt.setString(7, orders.getAddress());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public boolean update(Orders orders) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call  PROC_ORDERS_add(?,?,?,?,?,?,?,?)");
            callSt.setInt(1, orders.getId());
            callSt.setInt(2, orders.getUser_id());
            callSt.setDate(3, (Date) orders.getCreatedDate());
            callSt.setBoolean(4, orders.getType());
            callSt.setString(5, orders.getReceiver());
            callSt.setInt(6, orders.getStatus());
            callSt.setString(7, orders.getPhone());
            callSt.setString(8, orders.getAddress());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public Orders findById(int id) {
        Connection conn = null;
        Orders orders = new Orders();
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call  PROC_ORDERS_findById(?)");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                orders.setId(rs.getInt("id"));
                orders.setUser_id(rs.getInt("user_id"));
                orders.setCreatedDate(rs.getDate("createdDate"));
                orders.setType(rs.getBoolean("type"));
                orders.setReceiver(rs.getString("receiver"));
                orders.setStatus(rs.getInt("status"));
                orders.setPhone(rs.getString("phone"));
                orders.setAddress(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return orders;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call  PROC_ORDERS_delete(?)");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return false;
    }


    @Override
    public boolean createCart(int userId) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call  PROC_Create_cart(?)");
            callSt.setInt(1, userId);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public List<Orders> findOrdersByUserId(int userId) {
        List<Orders> listOrders = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("{call PROC_ORDERS_findOrdersByUserId(?)}");
            callSt.setInt(1,userId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setId(rs.getInt("id"));
                orders.setUser_id(rs.getInt("user_id"));
                orders.setCreatedDate(rs.getDate("createdDate"));
                orders.setType(rs.getBoolean("type"));
                orders.setReceiver(rs.getString("receiver"));
                orders.setStatus(rs.getInt("status"));
                orders.setPhone(rs.getString("phone"));
                orders.setAddress(rs.getString("address"));
                listOrders.add(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return listOrders;
    }
    @Override
    public Orders findOrdersTypeZero(int id){
        Connection conn = null;
        Orders orders = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callableStatement = conn.prepareCall("{call PROC_ORDERS_findOrdersTypeZero(?)}");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int oId = rs.getInt(1);
                int user_id = rs.getInt(2);
                Date createDate = rs.getDate(3);
                boolean type = rs.getBoolean(4);
                String receiver = rs.getString(5);
                int status = rs.getInt(6);
                String phone = rs.getString(7);
                String address = rs.getString(8);
                orders = new Orders(oId, user_id, createDate, type, receiver, status, phone, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return  orders;
    }

    @Override
    public int getLastInsertOrderId() {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callableStatement = conn.prepareCall("{call  PROC_ORDERS_getLastInsertOrderId()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return 0;
    }
}
