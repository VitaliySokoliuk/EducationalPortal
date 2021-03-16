package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> getByAuthor(User author);

    @Query("select c.logoPicture from Course c where c.id = :id")
    byte[] getLogoPictureById(int id);

}
