package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    List<Answer> findAllByHometaskIdOrderByMark(int hometastId);

    List<Answer> findAllByUserId(int userId);

    @Query("select an from Answer an join Hometask ht on an.hometask.id = ht.id " +
            "join Article a on a.hometask.id = ht.id where a.author.id = :authorId order by an.mark")
    List<Answer> findAllForAuthor(int authorId);

    void deleteAllByHometask_Id(int hometaskId);

}
