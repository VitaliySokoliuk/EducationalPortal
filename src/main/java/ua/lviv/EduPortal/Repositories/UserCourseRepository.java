package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.DTOs.UserDto;
import ua.lviv.EduPortal.Entities.CompositKeys.CK_UserCourse;
import ua.lviv.EduPortal.Entities.UserArticle;
import ua.lviv.EduPortal.Entities.UserCourse;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, CK_UserCourse> {

    @Query("select new ua.lviv.EduPortal.DTOs.UserDto(u.id, u.firstName, u.lastName, u.email, u.profilePicture) " +
            "from UserCourse uar join User u on uar.user.id = u.id where uar.course.id = :courseId")
    List<UserDto> findAllUsersByCourseId(int courseId);

    @Modifying
    @Query(value = "INSERT user_course(course_id, user_id, bought) VALUES (?1, ?2, ?3)",
            nativeQuery = true)
    void save(int cId, int uId, boolean bought);

    @Query("select u from UserCourse u where u.user.id = :uId and u.course.id = :cId")
    Optional<UserCourse> findByUserIdAndCourseId(int cId, int uId);

    @Modifying
    @Query("delete from UserCourse a where a.course.id = :cId and a.user.id = :uId")
    void delByCourseIdAndUserId(int cId, int uId);

    @Modifying
    void deleteAllByCourse_Id(int courseId);

}
