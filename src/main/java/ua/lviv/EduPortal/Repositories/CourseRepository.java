package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.DTOs.CourseDto;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> getByAuthor(User author);

    @Query("select c.logoPicture from Course c where c.id = :id")
    byte[] getLogoPictureById(int id);

    @Query("select c from Course c where c.visibility = true")
    List<Course> findAllIfNotPrivate();

    @Query("select c from Course c join UserCourse uc on c.id = uc.course.id " +
            "where uc.user.id = :userId and uc.addedByAuthor = :byAuthor")
    List<Course> findCoursesInUserList(int userId, boolean byAuthor);

    @Query("select new ua.lviv.EduPortal.DTOs.CourseDto(c.id, c.title, c.description, c.logoPicture, count(cl.id), " +
            "sum(case when cl.user.id = :userId then 1 else 0 end) > 0) " +
            "from Course c join UserCourse uc on c.id = uc.course.id " +
            "left join CourseLike cl on c.id = cl.course.id " +
            "where uc.user.id = :userId and uc.addedByAuthor = 0 " +
            "group by uc.course.id")
    List<CourseDto> findCoursesAndLikes(int userId);

    @Query("select c from Course c where c.visibility = true and c.chapter.topic.name = :topicName")
    List<Course> findAllByTopicIfNotPrivate(String topicName);

}
