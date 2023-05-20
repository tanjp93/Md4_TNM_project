package ra.model.service.OrderDetail;

import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;
import ra.model.until.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetail_IMPL implements IOrderDetail_Service<OrderDetail> {

    @Override
    public List<OrderDetail> findAll() {
        List<OrderDetail> listOrdersDetail = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_ORDER_DETAIL_findAll()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrder_id(rs.getInt("order_id"));
                orderDetail.setProduct_id(rs.getInt("product_id"));
                orderDetail.setProduct_price(rs.getFloat("product_price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                listOrdersDetail.add(orderDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return listOrdersDetail;
    }

    @Override
    public boolean save(OrderDetail orderDetail) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_ORDER_DETAIL_save(?,?,?,?)");
            callSt.setInt(1, orderDetail.getOrder_id());
            callSt.setInt(2, orderDetail.getProduct_id());
            callSt.setFloat(3, orderDetail.getProduct_price());
            callSt.setInt(4, orderDetail.getQuantity());
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
    public boolean update(OrderDetail orderDetail) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_ORDER_DETAIL_update(?,?,?,?,?)");
            callSt.setInt(1, orderDetail.getId());
            callSt.setInt(2, orderDetail.getOrder_id());
            callSt.setInt(3, orderDetail.getProduct_id());
            callSt.setFloat(4, orderDetail.getProduct_price());
            callSt.setInt(5, orderDetail.getQuantity());
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
    public OrderDetail findById(int id) {
        Connection conn = null;
        OrderDetail orderDetail = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_ORDER_DETAIL_findOrderDetailById(?)");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                orderDetail = createOrderDetail(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return orderDetail;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callSt = conn.prepareCall("call PROC_ORDER_DETAIL_delete(?)");
            callSt.setInt(1, id);
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
    public OrderDetail findOrderDetailByOrderIdAndProductId(int orderId, int productId) {
        Connection conn = null;
        OrderDetail orderDetail = null;
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callableStatement = conn.prepareCall("{call PROC_ORDER_DETAIL_findOrderDetailByOrderIdAndProductId(?, ?)}");
            callableStatement.setInt(1, orderId);
            callableStatement.setInt(2, productId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                orderDetail = createOrderDetail(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return orderDetail;
    }

    private OrderDetail createOrderDetail(ResultSet rs) {
        OrderDetail orderDetail = null;
        try {
            orderDetail = new OrderDetail();
            orderDetail.setId(rs.getInt("id"));
            orderDetail.setOrder_id(rs.getInt("order_id"));
            orderDetail.setProduct_id(rs.getInt("product_id"));
            orderDetail.setProduct_price(rs.getFloat("product_price"));
            orderDetail.setQuantity(rs.getInt("quantity"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetail;
    }
    public List<OrderDetail> findOrderDetailByOrderId(int orderId){
        Connection conn = null;
        List <OrderDetail> orderDetailList = new ArrayList<>();
        try {
            conn = ConnectionToDB.getConnectionToDB();
            CallableStatement callableStatement = conn.prepareCall("{call PROC_ORDER_DETAIL_findOrderDetailByOrderId(?)}");
            callableStatement.setInt(1, orderId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
              OrderDetail  orderDetail = createOrderDetail(rs);
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.closeConnection(conn);
        }
        return orderDetailList;
    }
}
