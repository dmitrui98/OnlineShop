package by.dmitrui98.service.implementation;

import by.dmitrui98.entity.Admin;
import by.dmitrui98.entity.enums.UserRoleEnum;
import by.dmitrui98.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Администратор on 20.04.2017.
 */
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminService adminService;

    //@Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        Admin admin = adminService.getByName(adminName);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.ADMIN.name()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(admin.getLogin(), admin.getPassword(), roles);

        return userDetails;
    }
}
