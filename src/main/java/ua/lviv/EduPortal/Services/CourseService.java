package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.DTOs.CourseDto;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getByAuthor(User author){
        return courseRepository.getByAuthor(author);
    }

    @Transactional
    public byte[] getLogoPictureById(int id) {
        return courseRepository.getLogoPictureById(id);
    }

    public Course findById(int id) {
        return courseRepository.getOne(id);
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public List<Course> findCoursesInUserList(int userId, boolean byAuthor){
        return courseRepository.findCoursesInUserList(userId, byAuthor);
    }

    public List<CourseDto> findCoursesAndLikes(int userId){
        return courseRepository.findCoursesAndLikes(userId);
    }

    public List<Course> findAllByTopic(String topicName){
        return courseRepository.findAllByTopic(topicName);
    }

    public List<Course> findFewByLikes(int count){
        return courseRepository.findFewByLikes(PageRequest.of(0,count));
    }

    public List<CourseDto> findAllCoursesAndLikes(int userId){
        return courseRepository.findAllCoursesAndLikes(userId);
    }

}
