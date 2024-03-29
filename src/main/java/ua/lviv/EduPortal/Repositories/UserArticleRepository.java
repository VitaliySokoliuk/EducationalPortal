package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.DTOs.UserDto;
import ua.lviv.EduPortal.Entities.CompositKeys.CK_UserArticle;
import ua.lviv.EduPortal.Entities.UserArticle;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserArticleRepository extends JpaRepository<UserArticle, CK_UserArticle> {

    @Query("select new ua.lviv.EduPortal.DTOs.UserDto(u.id, u.firstName, u.lastName, u.email, u.profilePicture) " +
            "from UserArticle uar join User u on uar.user.id = u.id where uar.article.id = :articleId")
    List<UserDto> findAllUsersByArticleId(int articleId);

    @Modifying
    @Query(value = "INSERT user_article(article_id, user_id, bought) VALUES (?1, ?2, ?3)",
            nativeQuery = true)
    void save(int aId, int uId, boolean bought);

    @Query("select u from UserArticle u where u.user.id = :uId and u.article.id = :aId")
    Optional<UserArticle> findByUserIdAndArticleId(int aId, int uId);

    @Modifying
    @Query("delete from UserArticle a where a.article.id = :aId and a.user.id = :uId")
    void delByArticleIdAndUserId(int aId, int uId);

    @Modifying
    void deleteAllByArticle_Id(int articleId);

}
