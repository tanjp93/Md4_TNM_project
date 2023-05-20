package ra.model.service.OrderDetail;

import ra.model.entity.OrderDetail;
import ra.model.service.IService;

public interface IOrderDetail_Service<O> extends IService<OrderDetail> {
    OrderDetail findOrderDetailByOrderIdAndProductId(int orderId, int productId);
}
