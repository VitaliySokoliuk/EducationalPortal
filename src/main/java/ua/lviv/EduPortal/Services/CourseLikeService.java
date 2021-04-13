package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.EduPortal.Entities.ArticleLike;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.CourseLike;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Repositories.CourseLikeRepository;

import java.util.Optional;

@Service
public class CourseLikeService {

    private CourseLikeRepository courseLikeRepository;

    @Autowired
    public CourseLikeService(CourseLikeRepository courseLikeRepository) {
        this.courseLikeRepository = courseLikeRepository;
    }

    public void saveOrDelete(User user, Course course){
        Optional<CourseLike> maybeLike = courseLikeRepository.findByUserAndCourse(user, course);
        if(maybeLike.isPresent()){
            courseLikeRepository.delete(maybeLike.get());
        }else{
            courseLikeRepository.save(new CourseLike(course, user));
        }
    }

    public int getLikesByUserId(int userId){
        return courseLikeRepository.getLikesByUserId(userId);
    }

    @Transactional
    public void deleteAllByCourseId(int cId){
        courseLikeRepository.deleteAllByCourse_Id(cId);
    }

}
