package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.ArticleLike;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Repositories.ArticleLikeRepository;

import java.util.Optional;

@Service
public class ArticleLikeService {

    private ArticleLikeRepository articleLikeRepository;

    @Autowired
    public ArticleLikeService(ArticleLikeRepository articleLikeRepository) {
        this.articleLikeRepository = articleLikeRepository;
    }

    public void saveOrDelete(User user, Article article){
        Optional<ArticleLike> maybeLike = articleLikeRepository.findByUserAndArticle(user, article);
        if(maybeLike.isPresent()){
            articleLikeRepository.delete(maybeLike.get());
        }else{
            articleLikeRepository.save(new ArticleLike(article, user));
        }
    }

//    public int getLikesByArticleId(int articleId){
//        return articleLikeRepository.getLikesByArticleId(articleId);
//    }
//
//    public boolean ifLikePresent(int userId, int articleId) {
//        return articleLikeRepository.existsByUserIdAndArticleId(userId, articleId);
//    }

}
