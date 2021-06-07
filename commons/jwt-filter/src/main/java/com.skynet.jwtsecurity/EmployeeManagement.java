package com.skynet.jwtsecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

public class EmployeeManagement extends User {

    public EmployeeManagement(final String username,
                              final String password,
                              final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public EmployeeManagement(String username, String password, boolean enabled,
                              boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                              Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    }
}
