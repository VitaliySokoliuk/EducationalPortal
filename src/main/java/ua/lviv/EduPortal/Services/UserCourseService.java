package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.EduPortal.DTOs.UserDto;
import ua.lviv.EduPortal.Entities.UserCourse;
import ua.lviv.EduPortal.Repositories.UserCourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserCourseService {

    private UserCourseRepository userCourseRepository;

    @Autowired
    public UserCourseService(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    public List<UserDto> findAllUsersByCourseId(int courseId){
        return userCourseRepository.findAllUsersByCourseId(courseId);
    }

    @Transactional
    public void save(int cId, int uId, boolean bought){
        if(userCourseRepository.findByUserIdAndCourseId(cId, uId).isEmpty()){
            userCourseRepository.save(cId, uId, bought);
        }
    }

    @Transactional
    public void delete(int cId, int uId){
        if(userCourseRepository.findByUserIdAndCourseId(cId, uId).isPresent()){
            userCourseRepository.delByCourseIdAndUserId(cId, uId);
        }
    }

    public Optional<UserCourse> findByUserIdAndCourseId(int cId, int uId){
        return userCourseRepository.findByUserIdAndCourseId(cId, uId);
    }

    @Transactional
    public void deleteAllByCourseId(int cId){
        userCourseRepository.deleteAllByCourse_Id(cId);
    }

}
