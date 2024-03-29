package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Entities.enums.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("select e.profilePicture from User e where e.id = :id")
    byte[] getProfilePictureById(int id);

    List<User> findAllByRole(UserRole userRole);

    Optional<User> findById(int id);

    List<User> findAllByNonLockedFalse();

    Optional<User> findByVerifyEmailHash(String hash);

    @Modifying
    @Query("Update User u set u.isEmailVerified = true where u.id = :id")
    void confirmEmail(@Param("id") int userId);

}
