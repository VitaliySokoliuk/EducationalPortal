package ua.lviv.EduPortal.Services;

import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.DTOs.ArticleDto;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Repositories.ArticleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> getByAuthor(User author){
        return articleRepository.getByAuthor(author);
    }

    @Transactional
    public byte[] getLogoPictureById(int id) {
        return articleRepository.getLogoPictureById(id);
    }

    public Article findById(int id){
        return articleRepository.getOne(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAllIfNotPrivate() {
        return articleRepository.findAllIfNotPrivate();
    }

    public List<Article> findArticlesInUserList(int userId, boolean byAuthor){
        return articleRepository.findArticlesInUserList(userId, byAuthor);
    }

    public int findHTidByArticleId(int articleId){
        return articleRepository.findHTidByArticleId(articleId);
    }

    public Article findByHometaskId(int hometaskId) {
        return articleRepository.findByHometaskId(hometaskId);
    }

    public List<ArticleDto> findArticlesAndLikes(int userId){
        return articleRepository.findArticlesAndLikes(userId);
    }

    public List<Article> findAllByTopicIfNotPrivate(String topicName){
        return articleRepository.findAllByTopicIfNotPrivate(topicName);
    }

}
