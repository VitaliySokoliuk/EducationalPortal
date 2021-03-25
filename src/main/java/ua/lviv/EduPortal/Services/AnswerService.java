package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.Answer;
import ua.lviv.EduPortal.Repositories.AnswerRepository;

import java.util.List;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> findAllByHometaskId(int id){
        return answerRepository.findAllByHometaskId(id);
    }

    public Answer findById(int answerId) {
        return answerRepository.getOne(answerId);
    }
}
