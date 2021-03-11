package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.Hometask;
import ua.lviv.EduPortal.Repositories.HometaskRepository;

@Service
public class HometaskService {

    private HometaskRepository hometaskRepository;

    @Autowired
    public HometaskService(HometaskRepository hometaskRepository) {
        this.hometaskRepository = hometaskRepository;
    }

    public Hometask save(Hometask task) {
        return hometaskRepository.save(task);
    }
}
