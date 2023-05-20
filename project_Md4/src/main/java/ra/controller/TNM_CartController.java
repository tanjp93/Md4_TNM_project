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
    IOrdersService ordersService = new OrdersIMPL();
    IOrderDetail_Service orderDetailService = new OrderDetail_IMPL();

    @PostMapping("/orderDetail")
    public String create_oderDetail(@ModelAttribute("orderDetail") OrderDetail orderDetail, HttpSession session, Model model) {
        UserLogin userLogin= (UserLogin) session.getAttribute("userLogin");
       //create cart or add to cart
        Orders orders= ordersService.findOrdersTypeZero(userLogin.getId());
        if (orders==null){
//            create cart
            ordersService.createCart(userLogin.getId());
            orderDetail.setOrder_id(ordersService.getLastInsertOrderId());
            try {
                orderDetailService.save(orderDetail);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            orderDetail.setOrder_id(orders.getId());
            OrderDetail tempOrderDetail=orderDetailService.findOrderDetailByOrderIdAndProductId(orders.getId(), orderDetail.getProduct_id());
            if (tempOrderDetail==null){
                try {
                    orderDetailService.save(orderDetail);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else {
                int quantity=tempOrderDetail.getQuantity()+orderDetail.getQuantity();
                tempOrderDetail.setQuantity(quantity);
                orderDetailService.update(tempOrderDetail);
            }
        }
        Orders orders1=ordersService.findOrdersTypeZero(userLogin.getId());
        List<OrderDetail> orderDetailList=orderDetailService.findOrderDetailByOrderId(orders1.getId());
        model.addAttribute("orderDetailList",orderDetailList);
        return "/order";
    }
    @GetMapping("/UserLoginOrder")
    public String toViewUserOrderDetail(HttpSession session,Model model){
        showUserLoginOrderDetail(session, model);
        return "/order";
    }


    @GetMapping("/deleteOrderDetail/{orderDetail_id}")
    public String toDeleteOrderDetail(@PathVariable("orderDetail_id") String id,HttpSession session,Model model){
        System.out.println("id delete >>>"+id);
        orderDetailService.delete(Integer.parseInt(id));
        showUserLoginOrderDetail(session, model);
        return "/order";
    }
    private void showUserLoginOrderDetail(HttpSession session, Model model) {
        UserLogin userLogin= (UserLogin) session.getAttribute("userLogin");
        Orders orders1=ordersService.findOrdersTypeZero(userLogin.getId());
        List<OrderDetail> orderDetailList=orderDetailService.findOrderDetailByOrderId(orders1.getId());
        model.addAttribute("orderDetailList",orderDetailList);
    }
}
