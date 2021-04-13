package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Entities.enums.UserRole;
import ua.lviv.EduPortal.Repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final UserRole DEFAULT_USER_ROLE = UserRole.ROLE_USER;

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user){
        user.setRole(DEFAULT_USER_ROLE);
        user.setPaidMaterials(false);
        user.setNonLocked(true);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void updateWithPass(User user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public byte[] getProfilePictureById(int id) {
        return userRepository.getProfilePictureById(id);
    }

    public List<User> findAllByRole(UserRole userRole){
        return userRepository.findAllByRole(userRole);
    }

    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    public List<User> findAllByNonLockedFalse(){
        return userRepository.findAllByNonLockedFalse();
    }

}
