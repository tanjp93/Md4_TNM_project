package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manageController")
public class TNM_Manage {
    @GetMapping("")
//    public String managePage(HttpServletRequest request){
    public String managePage(HttpSession session, Model model){
        User user = (User) session.getAttribute("userLogin");
//        User user = (User) request.getSession().getAttribute("userLogin");
        model.addAttribute("userLogin",user);
        return "/manage";
    }
}
