package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.EduPortal.DTOs.UserDto;
import ua.lviv.EduPortal.Entities.UserArticle;
import ua.lviv.EduPortal.Entities.UserCourse;
import ua.lviv.EduPortal.Repositories.UserArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserArticleService {

    private UserArticleRepository userArticleRepository;

    @Autowired
    public UserArticleService(UserArticleRepository userArticleRepository) {
        this.userArticleRepository = userArticleRepository;
    }

    public List<UserDto> findAllUsersByArticleId(int articleId){
        return userArticleRepository.findAllUsersByArticleId(articleId);
    }

    @Transactional
    public void save(int aId, int uId, boolean bought){
        if(userArticleRepository.findByUserIdAndArticleId(aId, uId).isEmpty()){
            userArticleRepository.save(aId, uId, bought);
        }
    }

    @Transactional
    public void delete(int aId, int uId){
        if(userArticleRepository.findByUserIdAndArticleId(aId, uId).isPresent()){
            userArticleRepository.delByArticleIdAndUserId(aId, uId);
        }
    }

    public Optional<UserArticle> findByUserIdAndArticleId(int aId, int uId){
        return userArticleRepository.findByUserIdAndArticleId(aId, uId);
    }

    @Transactional
    public void deleteAllByArticleId(int aId){
        userArticleRepository.deleteAllByArticle_Id(aId);
    }

}
