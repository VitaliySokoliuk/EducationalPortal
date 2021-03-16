package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.Hometask;
import ua.lviv.EduPortal.Repositories.HometaskRepository;

import java.util.Optional;

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

    public Optional<Hometask> findById(int id){
        return hometaskRepository.findById(id);
    }

    public void deleteById(int id){
        hometaskRepository.deleteById(id);
    }

}
