package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.EduPortal.DTOs.UserDto;
import ua.lviv.EduPortal.Entities.UserArticle;
import ua.lviv.EduPortal.Repositories.UserArticleRepository;

import java.util.ArrayList;
import java.util.List;

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
    public void save(int aId, int uId, boolean byUser){
        if(userArticleRepository.findByUserIdAndArticleId(aId, uId).isEmpty()){
            userArticleRepository.save(aId, uId, byUser);
        }
    }

    @Transactional
    public void delete(int aId, int uId){
        if(userArticleRepository.findByUserIdAndArticleId(aId, uId).isPresent()){
            userArticleRepository.delByArticleIdAndUserId(aId, uId);
        }
    }

}
