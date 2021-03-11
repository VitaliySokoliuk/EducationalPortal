package ua.lviv.EduPortal.Services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Repositories.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userMaybe = userRepository.findByEmail(email);
        return userMaybe
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user present with email: " + email));
    }

    public static Optional<User> getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new RuntimeException("");
        }
        Object obj = auth.getPrincipal();
        String username = "";
        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }
        Optional<User> u = userRepository.findByEmail(username);
        return u;
    }

}
