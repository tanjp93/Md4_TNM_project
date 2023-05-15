package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
//        if (user==null ){
//            return "redirect:/";
//        }
//        if (!user.getRole().toLowerCase().equals("admin")){
//            return "redirect:/";
//        }
        //check role admin
        return "manage";
    }

    @GetMapping("/createProduct")
    public ModelAndView backHome(HttpServletRequest request) {
        List<Category>categories=categoryService.findAll();
        request.getSession().setAttribute("categories",categories);
        System.out.println("id"+new Product().getId());
     return new ModelAndView("createAndEditProduct","product",new Product());
}
    @GetMapping("/editProduct/{id}")
    public ModelAndView toEditProduct(@PathVariable("id") String id,HttpServletRequest request){
        Product editProduct=productService.findById(Integer.parseInt(id));
        List<Category>categories=categoryService.findAll();
        request.getSession().setAttribute("categories",categories);
        System.out.println("img Update =>>>"+editProduct.getImg());
        return new ModelAndView("createAndEditProduct","product",editProduct);
    }
    @PostMapping("/updateProduct")
    public String doUpdateProduct(@ModelAttribute("product")Product updateProduct){
        System.out.println("update product -->>>>"+ updateProduct);
        productService.update(updateProduct);
        return "redirect:/manageController";
    };
    @PostMapping("/addProduct")
    public String doAddProduct(@ModelAttribute("product")Product newProduct){
        System.out.println("new product -->>>>"+ newProduct);
        productService.save(newProduct);
        return "redirect:/manageController";
    }
}
