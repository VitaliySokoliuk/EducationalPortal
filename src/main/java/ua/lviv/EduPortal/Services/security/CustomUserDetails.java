package ua.lviv.EduPortal.Services.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Entities.enums.UserRole;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = String.valueOf(user.getRole());
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }

    public boolean isAdmin(){
        return user.getRole() == UserRole.ROLE_ADMIN || user.getRole() == UserRole.ROLE_SUPER_ADMIN;
    }

    public boolean isSuperAdmin(){
        return user.getRole() == UserRole.ROLE_SUPER_ADMIN;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEmailVerified();
    }

}
