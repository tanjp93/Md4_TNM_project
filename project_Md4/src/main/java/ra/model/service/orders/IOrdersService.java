package ra.model.service.orders;

import ra.model.entity.Orders;
import ra.model.service.IService;

import java.util.List;

public interface IOrdersService<O> extends IService<Orders> {
    List<Orders> findOrdersByUserId(int id);
    boolean createCart(int userId);
    Orders findOrdersTypeZero(int id);
    int getLastInsertOrderId();
}
