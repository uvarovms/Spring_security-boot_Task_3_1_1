package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.servise.RoleService;
import web.servise.UserService;

import java.security.Principal;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin/admin_page")
    public String listUsers(Model model) {
        model.addAttribute("listRoles", roleService.getAllRoles());
        System.out.println("ВСЕ РОЛИ: " + roleService.getAllRoles());
        model.addAttribute("listUsers", userService.getAllUsers());
        System.out.println("ВСЕ ЮЗЕРЫ: " + userService.getAllUsers());
        return "admin_page";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, Set<Role> roles) {
        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user, Set<Role> roles) {
        userService.addUser(user);
        roleService.addRole(roles);
        return "redirect:/admin/admin_page";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("role", roleService.getRole(id));
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin/admin_page";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/admin_page";
    }

    @GetMapping("/user/user_page")
    public String getUserById(Model model, Principal principal) {
        model.addAttribute("listUser", userService.getUser(userService.getUserByLogin(principal.getName()).getId()));
        System.out.println("Логин принципиала: " + principal.getName());
        System.out.println("Мой юсер, который вошел: " + userService.getUserByLogin(principal.getName()));
        System.out.println("ID юсера: " + userService.getUserByLogin(principal.getName()).getId());
        return "user_page";
    }
}