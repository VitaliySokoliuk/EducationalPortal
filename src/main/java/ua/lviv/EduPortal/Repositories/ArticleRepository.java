package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.User;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> getByAuthor(User author);

    @Query("select a.logoPicture from Article a where a.id = :id")
    byte[] getLogoPictureById(int id);

}
