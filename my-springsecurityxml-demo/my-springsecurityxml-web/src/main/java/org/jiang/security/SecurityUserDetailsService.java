package org.jiang.security;

import org.jiang.springsecurity.pojo.Permission;
import org.jiang.springsecurity.pojo.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.*;

/**
 * 自定义UserDetailsService
 */
public class SecurityUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Resource(name = "bCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    private static Map<String, org.jiang.springsecurity.pojo.User> userMap = new HashMap<>();

    static {
        org.jiang.springsecurity.pojo.User adminUser = new org.jiang.springsecurity.pojo.User();
        adminUser.setUsername("admin");
        adminUser.setPassword("123");

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ADMIN");
        Set<Permission> permissionSet = new HashSet<>();
        Permission permission = new Permission();
        permission.setKeyword("add");
        permissionSet.add(permission);
        role.setPermissions(permissionSet);
        roles.add(role);

        adminUser.setRoles(roles);
        userMap.put(adminUser.getUsername(), adminUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.jiang.springsecurity.pojo.User user = userMap.get(username);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getKeyword()));
            for (Permission permission : permissions) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        UserDetails userDetails = new User(username, encodePassword, grantedAuthorityList);
        return userDetails;
    }
}
