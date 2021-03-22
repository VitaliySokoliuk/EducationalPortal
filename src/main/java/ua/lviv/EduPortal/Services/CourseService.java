package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.Article;
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

    public List<Course> findAllIfNotPrivate(){
        return courseRepository.findAllIfNotPrivate();
    }

}
