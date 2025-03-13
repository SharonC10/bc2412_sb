package com.ecommerceapp.simpleapp.controller.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ecommerceapp.simpleapp.controller.AdminOperation;
import com.ecommerceapp.simpleapp.entity.AdminEntity;
import com.ecommerceapp.simpleapp.entity.OrderEntity;
import com.ecommerceapp.simpleapp.entity.ProductEntity;
import com.ecommerceapp.simpleapp.entity.UserEntity;
import com.ecommerceapp.simpleapp.service.impl.AdminService;
import com.ecommerceapp.simpleapp.service.impl.OrderService;
import com.ecommerceapp.simpleapp.service.impl.ProductService;
import com.ecommerceapp.simpleapp.service.impl.UserService;

@Controller
public class AdminController implements AdminOperation {
  @Autowired
  private AdminService adminService;

  @Autowired
  private UserService userService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private ProductService productService;

  @GetMapping(value = "/admin/verify/credentials")
  public String verifyCredentials(@ModelAttribute("admin") AdminEntity admin,
      Model model)
   {
    if (adminService.verifyCredentials(admin.getEmail(), admin.getPassword())) {
      model.addAttribute("admin", new AdminEntity());
      model.addAttribute("user", new UserEntity());
      model.addAttribute("product", new ProductEntity());
      return "redirect:/admin/home";
    }

    model.addAttribute("error", "Invalid email or password");
    return "LoginPage";
  }
  
  @GetMapping("/admin/home")
  public String adminHomePage(Model model) {
    model.addAttribute("adminList", adminService.getAllAdmin());
    model.addAttribute("userList", userService.getAllUser());
    model.addAttribute("orderList", orderService.getAllOrder());
    model.addAttribute("productList", productService.getAllProduct());
//display in homepage 
    return "AdminHomePage";
  }

  @Override
  public String createAdmin(AdminEntity admin) {
    adminService.createUser(admin);
    return "redirect:/admin/home";
  }

  @Override
  public String update(Long id, Model model) {
    model.addAttribute("admin", adminService.getAdminById(id));
    return "UpdateAdmin";
  }

  @Override
  public String updateAdmin(AdminEntity admin) {
    adminService.updateAdmin(admin);
    return "redirect:/admin/home";
  }

  @Override
  public String deleteAdmin(@PathVariable Long id) {
    adminService.deleteAdmin(id);
    return "redirect:/admin/home";
  }

  @Override
  public String userHome(@ModelAttribute("userId") Long userId,
      @ModelAttribute("error") String error,
      @ModelAttribute("messageSuccess") String messageSuccess, Model model) {
    UserEntity user = userService.getUserById(userId);
    model.addAttribute("ordersList", orderService.findOrdersByUser(user));
    if (!error.isEmpty()) {
      model.addAttribute("error", error);
    }
    if (!messageSuccess.isEmpty()) {
      model.addAttribute("messageSuccess", messageSuccess);
    }

    return "BuyProductPage";
  }


  @Override
  public String userLogin(UserEntity user,
      RedirectAttributes redirectAttributes) {
    if (userService.verifyCredentials(user.getEmail(), user.getPassword())) {
      user = userService.findUserByEmail(user.getEmail());
      redirectAttributes.addAttribute("userId", user.getId());

      return "redirect:/user/home";
    }

    redirectAttributes.addAttribute("error", "Invalid email or password");
    return "Login";
  }


  @Override
  public String productSearch(String name, Long userId, Model model) {
    ProductEntity product = productService.findProductByName(name);
    UserEntity user = userService.getUserById(userId);
    model.addAttribute("ordersList", orderService.findOrdersByUser(user));

    if (product != null) {
      model.addAttribute("product", product);
    } else {
      model.addAttribute("messageError", "Sorry, product was not found...");
    }

    model.addAttribute("userId", userId);

    return "BuyProductPage";
  }


//---------------------------------------------------
//find order by user? 
 @Override
  public String placeOrder(OrderEntity order, Long userId,
      RedirectAttributes redirectAttributes) {
    double totalAmount = order.getPrice() * order.getQuantity();
    order.setAmount(totalAmount);
    order.setDate(new Date());

    UserEntity user = userService.getUserById(userId);
    order.setUser(user);

    orderService.createOrder(order);

    redirectAttributes.addAttribute("userId", userId);
    redirectAttributes.addAttribute("messageSuccess",
        "The order has been placed!!");

    return "redirect:/user/home";
  }
}
