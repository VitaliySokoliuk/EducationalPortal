package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.AnswerFile;

@Repository
public interface AnswerFileRepository extends JpaRepository<AnswerFile, Integer> {

}
