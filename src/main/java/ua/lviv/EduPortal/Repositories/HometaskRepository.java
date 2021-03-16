package ua.lviv.EduPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.EduPortal.Entities.Hometask;

@Repository
public interface HometaskRepository extends JpaRepository<Hometask, Integer> {
}
