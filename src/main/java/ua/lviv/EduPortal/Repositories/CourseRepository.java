package ua.lviv.EduPortal.Repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.DTOs.CourseDto;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> getByAuthor(User author);

    @Query("select c.logoPicture from Course c where c.id = :id")
    byte[] getLogoPictureById(int id);

    @Query("select c from Course c join UserCourse uc on c.id = uc.course.id " +
            "where uc.user.id = :userId and uc.bought = :bought")
    List<Course> findCoursesInUserList(int userId, boolean bought);

    @Query("select new ua.lviv.EduPortal.DTOs.CourseDto(c.id, c.title, c.description, c.logoPicture, c.paid, c.price, " +
            "count(cl.id), sum(case when cl.user.id = :userId then 1 else 0 end) > 0, c.author, c.chapter) " +
            "from Course c join UserCourse uc on c.id = uc.course.id " +
            "left join CourseLike cl on c.id = cl.course.id " +
            "where uc.user.id = :userId and uc.bought = :isBought " +
            "group by uc.course.id")
    List<CourseDto> findCoursesAndLikesAndPaid(int userId, boolean isBought);

    @Query("select c from Course c where c.chapter.topic.name = :topicName")
    List<Course> findAllByTopic(String topicName);

    @Query("select c from Course c where c.chapter.topic.name = :topicName and " +
            "(c.title like %:title% or c.chapter.name like %:title%)")
    List<Course> findAllByTopicAndTitle(String topicName, String title);

    @Query("select c from Course c where c.title like %:title% or c.chapter.name like %:title%")
    List<Course> findAllByTitle(String title);

    @Query("select c from Course c left join CourseLike ck on c.id = ck.course.id " +
            "group by ck.course.id order by count(ck.course.id) desc")
    List<Course> findFewByLikes(Pageable pageable);

    @Query("select new ua.lviv.EduPortal.DTOs.CourseDto(c.id, c.title, c.description, c.logoPicture, " +
            "c.paid, c.price, count(cl.course.id), c.author, c.chapter)" +
            "from Course c left join CourseLike cl on c.id = cl.course.id " +
            "where c.author.id = :userId group by c.id")
    List<CourseDto> findAllCoursesAndLikes(int userId);

}
