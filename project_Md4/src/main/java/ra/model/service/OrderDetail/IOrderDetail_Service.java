package ra.model.service.OrderDetail;

import ra.model.entity.OrderDetail;
import ra.model.service.IService;

import java.util.List;

public interface IOrderDetail_Service<O> extends IService<OrderDetail> {
    OrderDetail findOrderDetailByOrderIdAndProductId(int orderId, int productId);
    List<OrderDetail> findOrderDetailByOrderId(int orderId);
}
