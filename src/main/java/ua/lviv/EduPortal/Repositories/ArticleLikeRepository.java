package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.ArticleLike;
import ua.lviv.EduPortal.Entities.User;

import java.util.Optional;

@Repository
public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Integer> {

    Optional<ArticleLike> findByUserAndArticle(User user, Article article);

    @Query("select count(al) from ArticleLike al where al.article.author.id = :userId")
    int getLikesByUserId(int userId);

}
