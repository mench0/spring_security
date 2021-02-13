package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_panel";
    }

    @GetMapping("/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", userService.getAllRoles());
        return "new";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "role") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String role : roles) {
                roleSet.add(userService.getRole(role));
            }
        }
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    // получение(выбор) одного пользователя
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show-user";
    }

    //переход на страницу редактирования
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", userService.getAllRoles());
        return "edit";
    }

    // редактирование при нажатии кнопки
    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam(value = "role") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String role : roles) {
                roleSet.add(userService.getRole(role));
            }
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    //удаление пользователя
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
