package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.service.certificate.CertificateIMPL;
import ra.model.service.certificate.ICertificateService;
import ra.model.service.product.IProductService;
import ra.model.service.product.ProductServiceIMPL;
import ra.model.service.userService.IUserService;
import ra.model.service.userService.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class TNM_home_controller {
    IUserService userService = new UserServiceIMPL();
    IProductService productService=new ProductServiceIMPL();
    ICertificateService certificateService=new CertificateIMPL();

    @GetMapping({"/", "/home"})
    public String backHome(HttpServletRequest request, Model model, HttpSession session) {
        String action = request.getParameter("action");
        session.setAttribute("listProduct",productService.findAll());
        session.setAttribute("certificates",certificateService.findAll());
        if (action==null){
            action="home";
        }
        switch (action) {
            case "home":
                model.addAttribute("home","backToHome");
                return "index";
            case "manage":
                model.addAttribute("manage","manage");
                return "manageController";
            default:
                model.addAttribute("home","backToHome");
                return "index";
        }

    }

    @GetMapping("/formLogin")
    public ModelAndView formLogin() {
        return new ModelAndView("login", "userLogin", new User());
    }

    @GetMapping("/formRegister")
    public ModelAndView formRegister() {
        return new ModelAndView("register", "userRegister", new User());
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute("userRegister") User user, @RequestParam("rePassword") String rePassword, Model model) throws SQLException {

        if (user.getUserName().trim().equals("")) {
            model.addAttribute("userErr", "Vui lòng nhập tên đăng nhâp");
            return "/register";
        }
        if (user.getPassword().trim().equals("")) {
            model.addAttribute("passwordErr", "Vui lòng nhập mật khẩu");
            return "/register";
        }
        if (rePassword.trim().equals("")) {
            model.addAttribute("rePasswordErr", "Vui lòng nhập lại mật khẩu");
            return "/register";
        }
        if (user.getUserName().trim().length() < 3) {
            model.addAttribute("passwordErr", "Mật khẩu phải lớn hơn 4 kí tự");
            return "/register";
        }
        if (userService.checkExistsUsername(user.getUserName())) {
            model.addAttribute("checkFinal", "Tên đăng nhập không hợp lệ");
            return "/register";
        }
        if (!user.getPassword().trim().equals(rePassword)) {
            model.addAttribute("checkFinal", "Mật khẩu không trùng khớp");
            return "/register";
        }
        if (!userService.isValidPassword(user.getPassword())) {
            model.addAttribute("checkFinal", "Mật khẩu phải lớn hơn 6 kí tự , chứa ít nhất 1 ký tự hoa, 1 kí tự thường, 1 kí tự đặc biệt , 1 kí tự số !");
            return "/register";
        }
        userService.save(user);
        return "redirect:/formLogin";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute("userLogin") User user, Model model, HttpServletRequest request) {

        if (user.getUserName().trim().equals("")) {
            model.addAttribute("userLoginErr", "Vui lòng nhập tên đăng nhâp !");
            return "/login";
        }
        if (user.getPassword().trim().equals("")) {
            model.addAttribute("passwordLoginErr", "Vui lòng nhập mật khẩu !");
            return "/login";
        }
        UserLogin userLogin = userService.userLogin(user);
        if (userLogin == null) {
            model.addAttribute("loginErr", "Tên đăng nhập hoặc mật khẩu không chính xác !");
            return "/login";
        } else {
            request.getSession().setAttribute("userLogin", userLogin);
            if (userLogin.getRole()!=null & userLogin.getRole().equals("admin") ||userLogin.getRole()!=null &  userLogin.getRole().equals("manager")) {
                return "redirect:/manageController";
            } else {
                return "redirect:/";
            }
        }
    }
    @GetMapping("/logOut")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("userLogin");
        return "redirect:/";
    }
}
