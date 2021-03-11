package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
}
