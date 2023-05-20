package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.OrderDetail;
import ra.model.entity.Orders;
import ra.model.entity.UserLogin;
import ra.model.service.OrderDetail.IOrderDetail_Service;
import ra.model.service.OrderDetail.OrderDetail_IMPL;
import ra.model.service.orders.IOrdersService;
import ra.model.service.orders.OrdersIMPL;
import ra.model.service.product.ProductServiceIMPL;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/cartController")
public class TNM_CartController {
    ProductServiceIMPL productService = new ProductServiceIMPL();
    IOrdersService ordersService= new OrdersIMPL();
    IOrderDetail_Service orderDetailService=new OrderDetail_IMPL();
    @PostMapping("/orderDetail")
    public String create_oderDetail(@ModelAttribute("orderDetail")OrderDetail orderDetail, HttpSession session, Model model) throws SQLException {
        UserLogin userLogin= (UserLogin) session.getAttribute("userLogin");
        Orders orders = ordersService.findOrdersTypeZero(userLogin.getId());
        if (orders == null) {
//            // TODO create
//            // Insert
            ordersService.createCart(userLogin.getId());
            int lastInsertOrderId = ordersService.getLastInsertOrderId();
            orderDetailService.save(new OrderDetail(
                    lastInsertOrderId,
                    orderDetail.getProduct_id(),
                    orderDetail.getProduct_price(),
                    orderDetail.getQuantity()));
        } else {
            // TODO check exist --> true --> count +1
            // --> false --> save
            OrderDetail tempOrderDetail = orderDetailService.findOrderDetailByOrderIdAndProductId(orders.getId(), orderDetail.getProduct_id());
            if (tempOrderDetail == null) {
                orderDetailService.save(
                        new OrderDetail(
                        orders.getId(),
                        orderDetail.getProduct_id(),
                        orderDetail.getProduct_price(),
                        orderDetail.getQuantity()));
            } else {
                int newQuantity = tempOrderDetail.getQuantity() + orderDetail.getQuantity();
                tempOrderDetail.setQuantity(newQuantity);
                orderDetailService.update(tempOrderDetail);
            }
        }
//        List<Orders>ordersList
//        model.addAttribute("orderDetail",orderDetail);
//        Orders orders=ordersService.findById();
        return "/order";
    }
}
