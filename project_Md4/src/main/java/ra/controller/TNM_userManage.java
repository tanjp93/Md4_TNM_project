package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.User;
import ra.model.service.userService.IUserService;
import ra.model.service.userService.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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
    public String updateUser(@ModelAttribute("userLogin") User user, Model model, HttpSession session, @RequestParam("rePassword") String rePassword, @RequestParam("avatarUpload")MultipartFile avatarUpload){
        String uploadPathProducts = "C:\\Users\\user\\Desktop\\Rikkei\\MD4\\BT\\JSP-servlet\\102-project-MD4\\Part1\\project_Md4\\src\\main\\resources\\assets\\img\\users\\";
        //Upload IMG
        File file = new File(uploadPathProducts);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = avatarUpload.getOriginalFilename();
        if (fileName.length() == 0){
            fileName=userService.findById(user.getId()).getAvatar();
        }
        // coppy file upload đén thư mục chỉ đinh
        try {
            FileCopyUtils.copy(avatarUpload.getBytes(),new File(uploadPathProducts + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user.setAvatar(fileName);
        //Update IMG
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
