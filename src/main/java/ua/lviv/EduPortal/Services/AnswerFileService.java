package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.AnswerFile;
import ua.lviv.EduPortal.Repositories.AnswerFileRepository;

@Service
public class AnswerFileService {

    private AnswerFileRepository answerFileRepository;

    @Autowired
    public AnswerFileService(AnswerFileRepository answerFileRepository) {
        this.answerFileRepository = answerFileRepository;
    }

    public AnswerFile save(AnswerFile answerFile){
        return answerFileRepository.save(answerFile);
    }


    public AnswerFile getById(int id) {
        return answerFileRepository.getOne(id);
    }
}
