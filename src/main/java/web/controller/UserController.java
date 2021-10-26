package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.servise.RoleService;
import web.servise.UserService;
import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
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
        model.addAttribute("listUsers", userService.getAllUsers());
        return "admin_page";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user) {
        Set<Role> roleSet = new LinkedHashSet<>();
        user.getRoles().stream().forEach(role -> roleSet.add(roleService.findRole(role)));
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin/admin_page";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user) {
        Set<Role> roleSet = new LinkedHashSet<>();
        user.getRoles().stream().forEach(role -> roleSet.add(roleService.findRole(role)));
        user.setRoles(roleSet);
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
        return "user_page";
    }
}