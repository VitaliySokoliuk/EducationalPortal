package ua.lviv.EduPortal.Services;

import org.springframework.stereotype.Service;
import ua.lviv.EduPortal.Entities.Chapter;
import ua.lviv.EduPortal.Repositories.ChapterRepository;

import java.util.List;

@Service
public class ChapterService {

    private ChapterRepository chapterRepository;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }


    public List<Chapter> findAll() {
        return chapterRepository.findAll();
    }

    public void deleteById(int id) {
        chapterRepository.deleteById(id);
    }

    public Chapter save(Chapter newChapter) {
        return chapterRepository.save(newChapter);
    }



}
