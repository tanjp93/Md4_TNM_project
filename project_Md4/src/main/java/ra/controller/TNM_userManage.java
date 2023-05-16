package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.User;
import ra.model.service.userService.IUserService;
import ra.model.service.userService.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userManage")
public class TNM_userManage {
    IUserService userService=new UserServiceIMPL();
    @GetMapping("/personalInformation")
    public ModelAndView toUserManage(HttpSession session){
        User userLogin= (User) session.getAttribute("userLogin");
        if (userLogin==null){
            return new ModelAndView("/","newUser", new User());
        }
        return new ModelAndView("personalInfo","userLogin",userLogin);
    }
    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("userLogin") User user, Model model, HttpSession session,@RequestParam("rePassword") String rePassword){
        System.out.println("vào đây không?");
        if (user.getPassword().trim().equals("")) {
            model.addAttribute("checkErr", "Mật khẩu không được để trống!");
            return "personalInfo";
        }
        if (!userService.isValidPassword(user.getPassword())) {
            model.addAttribute("checkErr", "Mật khẩu phải lớn hơn 6 kí tự , chứa ít nhất 1 ký tự hoa, 1 kí tự thường, 1 kí tự đặc biệt , 1 kí tự số !");
            return "personalInfo";
        }
        if (rePassword.trim().equals("")) {
            model.addAttribute("checkErr", "Vui lòng xác nhận lại mật khẩu");
            return "personalInfo";
        }
        if (!user.getPassword().trim().equals(rePassword)) {
            model.addAttribute("checkErr", "Mật khẩu không trùng khớp");
            return "personalInfo";
        }
        userService.update(user);
        session.removeAttribute("userLogin");
        return "redirect:/formLogin";
    }
}
