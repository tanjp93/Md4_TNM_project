package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.service.category.CategoryService;
import ra.model.service.category.ICategoryService;
import ra.model.service.product.IProductService;
import ra.model.service.product.ProductServiceIMPL;
import ra.model.service.userService.IUserService;
import ra.model.service.userService.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manageController")
public class TNM_Manage {
    IProductService productService = new ProductServiceIMPL();
    IUserService userService = new UserServiceIMPL();
    ICategoryService categoryService=new CategoryService();

    @GetMapping("")
//    public String managePage(HttpServletRequest request){
    public String managePage(HttpSession session, Model model, HttpServletRequest request) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "displayUser";
        }
        switch (action) {
            case "displayUser":
                List<User> listUser = userService.findAll();
                model.addAttribute("displayUser", "displayUser");
                model.addAttribute("listUser", listUser);
                break;
            case "displayPro":
                List<Product> listProduct = productService.findAll();
                model.addAttribute("displayPro", "displayPro");
                model.addAttribute("listProduct", listProduct);

                break;
            case "displayOrder":
                model.addAttribute("displayOrder", "displayOrder");
                break;
            case "displayCertificate":
                model.addAttribute("displayCertificate", "displayCertificate");
                break;
            default:
                model.addAttribute("displayUser", "displayUser");
                break;
        }

        User user = (User) session.getAttribute("userLogin");
        //check role admin
        if (user==null ){
            return "redirect:/";
        }
        if (!user.getRole().toLowerCase().equals("admin")){
            return "redirect:/";
        }
//        check role admin
        return "manage";
    }

    @GetMapping("/createProduct")
    public ModelAndView backHome(HttpServletRequest request) {
        List<Category>categories=categoryService.findAll();
        request.getSession().setAttribute("categories",categories);
     return new ModelAndView("createAndEditProduct","product",new Product());
}
    @GetMapping("/editProduct/{id}")
    public ModelAndView toEditProduct(@PathVariable("id") String id,HttpServletRequest request){
        Product editProduct=productService.findById(Integer.parseInt(id));
        List<Category>categories=categoryService.findAll();
        request.getSession().setAttribute("categories",categories);
//        System.out.println("img Update =>>>"+editProduct.getImg());
        return new ModelAndView("createAndEditProduct","product",editProduct);
    }
    @GetMapping("/deleteProduct/{id}")
    public String doDelete(@PathVariable("id") String id){
//        System.out.println("id >>>"+id);
        productService.delete(Integer.parseInt(id));
        return "redirect:/manageController?action=displayPro";
    }
    @PostMapping("/updateProduct")
    public String doUpdateProduct(@ModelAttribute("product")Product updateProduct, @RequestParam("imgInput") MultipartFile image){
        String uploadPathProducts = "C:\\Users\\user\\Desktop\\Rikkei\\MD4\\BT\\JSP-servlet\\102-project-MD4\\Part1\\project_Md4\\src\\main\\resources\\assets\\img\\products\\";
        //Upload IMG
        File file = new File(uploadPathProducts);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        if (fileName.length() == 0){
             fileName=productService.findById(updateProduct.getId()).getImg();
        }
        // coppy file upload đén thư mục chỉ đinh
        try {
            FileCopyUtils.copy(image.getBytes(),new File(uploadPathProducts + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateProduct.setImg(fileName);
        //Update IMG
        productService.update(updateProduct);
        return "redirect:/manageController?action=displayPro";
    };
    @PostMapping("/addProduct")
    public String doAddProduct(@ModelAttribute("product")Product newProduct, @RequestParam("imgInput") MultipartFile image ){
        String uploadPathProducts = "C:\\Users\\user\\Desktop\\Rikkei\\MD4\\BT\\JSP-servlet\\102-project-MD4\\Part1\\project_Md4\\src\\main\\resources\\assets\\img\\products\\";
        //Upload IMG
        File file = new File(uploadPathProducts);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = image.getOriginalFilename();
        // coppy file upload đén thư mục chỉ đinh
        try {
            FileCopyUtils.copy(image.getBytes(),new File(uploadPathProducts + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newProduct.setImg(fileName);
        //Upload IMG
        productService.save(newProduct);
        return "redirect:/manageController?action=displayPro";
    }
}
