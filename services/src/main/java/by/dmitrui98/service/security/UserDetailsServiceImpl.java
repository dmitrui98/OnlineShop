package by.dmitrui98.service.security;

import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.User;
import by.dmitrui98.entity.enums.UserRoleEnum;
import by.dmitrui98.service.dao.AdminService;
import by.dmitrui98.service.dao.UserService;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getByName(name);
        Set<GrantedAuthority> roles = new HashSet<>();
        if (user == null) {
            // если user не найден, ищем администратора
            Admin admin = adminService.getByName(name);
            if (admin == null) {
                log.info("Пользователь с именем " + name + " не найден.");
                throw new UsernameNotFoundException("Пользователь с именем " + name + " не найден.");
            }
            roles.add(new SimpleGrantedAuthority("ROLE_" + UserRoleEnum.ADMIN.name()));
            return new org.springframework.security.core.userdetails.User(admin.getLogin(),
                    admin.getPassword(), roles);
        }
        roles.add(new SimpleGrantedAuthority("ROLE_" + UserRoleEnum.USER.name()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), roles);
    }
}
