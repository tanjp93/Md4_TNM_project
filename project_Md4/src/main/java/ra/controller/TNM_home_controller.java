package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.User;
import ra.model.service.userService.IUserService;
import ra.model.service.userService.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class TNM_home_controller {
    IUserService userService = new UserServiceIMPL();

    @GetMapping({"/", "/home"})
    public String backHome() {
        return "index";
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
    public String doRegister(@ModelAttribute("userRegister") User user, @RequestParam("rePassword") String rePassword, Model model) {
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
            model.addAttribute("passwordLoginErr", "Vui lòng nhập tên mật khẩu !");
            return "/login";
        }
        User userLogin = userService.userLogin(user);
        if (userLogin == null) {
            model.addAttribute("loginErr", "Tên đăng nhập hoặc mật khẩu không chính xác !");
            return "/login";
        } else {
            request.getSession().setAttribute("userLogin",userLogin);
            if (userLogin.getRole().equals("admin")||userLogin.getRole().equals("manager")){
                return "redirect:/manageController";
            }else {
            return "redirect:/";
            }
        }
    }
}