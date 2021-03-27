package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.DTOs.ArticleDto;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.User;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> getByAuthor(User author);

    @Query("select a.logoPicture from Article a where a.id = :id")
    byte[] getLogoPictureById(int id);

    @Query("select a from Article a where a.visibility = true")
    List<Article> findAllIfNotPrivate();

    @Query("select a from Article a join UserArticle uar on a.id = uar.article.id " +
            "where uar.user.id = :userId and uar.addedByAuthor = :byAuthor")
    List<Article> findArticlesInUserList(int userId, boolean byAuthor);

    @Query("select a.hometask.id from Article a where a.id = :articleId")
    int findHTidByArticleId(int articleId);

    Article findByHometaskId(int hometaskId);

    @Query("select new ua.lviv.EduPortal.DTOs.ArticleDto(a.id, a.title, a.description, a.logoPicture, count(al.id), " +
            "sum(case when al.user.id = :userId then 1 else 0 end) > 0) " +
            "from Article a join UserArticle uar on a.id = uar.article.id " +
            "left join ArticleLike al on a.id = al.article.id " +
            "where uar.user.id = :userId and uar.addedByAuthor = 0 " +
            "group by uar.article.id")
    List<ArticleDto> findArticlesAndLikes(int userId);

    @Query("select a from Article a where a.visibility = true and a.chapter.topic.name = :topicName")
    List<Article> findAllByTopicIfNotPrivate(String topicName);

}
