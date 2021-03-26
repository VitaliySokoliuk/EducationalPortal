package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.CourseLike;
import ua.lviv.EduPortal.Entities.User;

import java.util.Optional;

@Repository
public interface CourseLikeRepository extends JpaRepository<CourseLike, Integer> {

    Optional<CourseLike> findByUserAndCourse(User user, Course course);

}
