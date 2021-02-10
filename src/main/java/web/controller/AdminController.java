package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin_panel";
    }

    //сохранение пользвателя
    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "admin_panel";
    }

    //переход на страницу создания пользователя
    @GetMapping("/create")
    public String create(@ModelAttribute("user") User user){
        return "new";
    }

    //удаление пользователя
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
