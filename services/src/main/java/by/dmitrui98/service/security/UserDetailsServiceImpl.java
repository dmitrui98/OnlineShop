package by.dmitrui98.service.security;

import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.User;
import by.dmitrui98.entity.enums.UserRoleEnum;
import by.dmitrui98.service.dao.AdminService;
import by.dmitrui98.service.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Администратор on 18.04.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = null;
        try {
            user = userService.getByName(name);
        } catch (Exception ex) {
            System.out.println("Пользователь с именем " + name + " не найден.");
        }
        Set<GrantedAuthority> roles = new HashSet();
        UserDetails userDetails = null;
        if (user != null) {
            roles.add(new SimpleGrantedAuthority("ROLE_"+UserRoleEnum.USER.name()));
            userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(),
                    user.getPassword(), roles);
        } else {
            Admin admin = adminService.getByName(name);
            roles.add(new SimpleGrantedAuthority("ROLE_"+UserRoleEnum.ADMIN.name()));
            userDetails = new org.springframework.security.core.userdetails.User(admin.getLogin(),
                    admin.getPassword(), roles);
        }


        return userDetails;
    }
}
