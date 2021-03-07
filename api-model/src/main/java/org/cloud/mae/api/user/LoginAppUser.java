package org.cloud.mae.api.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Mae
 * @Date: 2021/3/4 9:26 上午
 *
 * this is the spring security current login user.
 *
 */
@Setter
@Getter
public class LoginAppUser extends AppUser implements UserDetails {
    private static final long serialVersionUID = 123456787654346543L;

    private Set<SysRole> sysRoles;

    private Set<String> permissions;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        if (!CollectionUtils.isEmpty(sysRoles)) {
            sysRoles.forEach(role -> {
                if (role.getCode().startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(role.getCode()));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
                }
            });
        }

        if (!CollectionUtils.isEmpty(permissions)) {
            permissions.forEach(per -> {
                collection.add(new SimpleGrantedAuthority(per));
            });
        }
        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
