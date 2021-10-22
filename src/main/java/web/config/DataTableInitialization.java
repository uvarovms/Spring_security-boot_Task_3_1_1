package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.servise.RoleService;
import web.servise.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataTableInitialization {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataTableInitialization(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        User user1 = new User();
        user1.setName("Anton");
        user1.setLastName("Tikhonov");
        user1.setAge((byte) 25);
        user1.setEmail("1@mail.ru");
        user1.setPassword("111");

        User user2 = new User();
        user2.setName("Sergey");
        user2.setLastName("Litvinov");
        user2.setAge((byte) 35);
        user2.setEmail("2@mail.ru");
        user2.setPassword("222");

        Role role1 = new Role();
        role1.setName("ROLE_USER");
        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        Set<Role> setRoles = new HashSet<>();
        setRoles.add(role1);
        setRoles.add(role2);
        roleService.addRole(setRoles);

        user1.setRoles(setRoles);
        user2.setRoles(setRoles.stream().limit(1).collect(Collectors.toSet()));

        userService.addUser(user1);
        userService.addUser(user2);
    }
}
