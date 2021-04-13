package ua.lviv.EduPortal.Repositories;

import org.springframework.data.domain.Pageable;
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

    @Query("select a from Article a join UserArticle uar on a.id = uar.article.id " +
            "where uar.user.id = :userId and uar.bought = :bought")
    List<Article> findArticlesInUserList(int userId, boolean bought);

    @Query("select a.hometask.id from Article a where a.id = :articleId")
    int findHTidByArticleId(int articleId);

    Article findByHometaskId(int hometaskId);

    @Query("select new ua.lviv.EduPortal.DTOs.ArticleDto(a.id, a.title, a.description, a.logoPicture, a.paid, a.price, " +
            "count(al.id), sum(case when al.user.id = :userId then 1 else 0 end) > 0) " +
            "from Article a join UserArticle uar on a.id = uar.article.id " +
            "left join ArticleLike al on a.id = al.article.id " +
            "where uar.user.id = :userId and uar.bought = :isBought " +
            "group by uar.article.id")
    List<ArticleDto> findArticlesAndLikesAndPaid(int userId, boolean isBought);

    @Query("select a from Article a where a.chapter.topic.name = :topicName")
    List<Article> findAllByTopic(String topicName);

    @Query("select a from Article a where a.chapter.topic.name = :topicName and " +
            "(a.title like %:title% or a.chapter.name like %:title%)")
    List<Article> findAllByTopicAndTitle(String topicName, String title);

    @Query("select a from Article a where a.title like %:title% or a.chapter.name like %:title%")
    List<Article> findAllByTitle(String title);

    @Query("select a from Article a left join ArticleLike ak on a.id = ak.article.id " +
            "group by ak.article.id order by count(ak.article.id) desc")
    List<Article> findFewByLikes(Pageable pageable);

    @Query("select new ua.lviv.EduPortal.DTOs.ArticleDto(a.id, a.title, a.description, a.logoPicture, " +
            "a.paid, a.price, a.giveAnswers, count(al.article.id) )" +
            "from Article a left join ArticleLike al on a.id = al.article.id " +
            "where a.author.id = :userId group by a.id")
    List<ArticleDto> findAllArticlesAndLikes(int userId);

}
