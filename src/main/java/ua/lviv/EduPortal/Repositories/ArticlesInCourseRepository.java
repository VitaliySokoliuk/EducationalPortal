package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.ArticlesInCourse;
import ua.lviv.EduPortal.Entities.CompositKeys.CK_ArticlesInCourse;

import java.util.List;

@Repository
public interface ArticlesInCourseRepository extends JpaRepository<ArticlesInCourse, CK_ArticlesInCourse> {

    @Query("select a.article from ArticlesInCourse a where a.course.id = :courseId")
    List<Article> findArticlesByCourseId(int courseId);

    @Modifying
    @Query(value = "INSERT articles_in_course(article_id, course_id) VALUES (?1, ?2)",
            nativeQuery = true)
    void save(int aId, int cId);

    @Modifying
    @Query("delete from ArticlesInCourse a where a.course.id = :course_id and a.article.id = :article_id")
    void delete(int course_id, int article_id);

    @Modifying
    void deleteAllByCourse_Id(int courseId);

    @Modifying
    void deleteAllByArticle_Id(int articleId);

}
