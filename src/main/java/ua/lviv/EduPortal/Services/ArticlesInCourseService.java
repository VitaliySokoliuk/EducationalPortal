package ua.lviv.EduPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.ArticlesInCourse;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Repositories.ArticlesInCourseRepository;

import java.util.List;

@Service
public class ArticlesInCourseService {

    private ArticlesInCourseRepository articlesInCourseRepository;

    @Autowired
    public ArticlesInCourseService(ArticlesInCourseRepository articlesInCourseRepository) {
        this.articlesInCourseRepository = articlesInCourseRepository;
    }

    public List<Article> findArticlesByCourseId(int courseId){
        return articlesInCourseRepository.findArticlesByCourseId(courseId);
    }

    @Transactional
    public void save(int aId, int cId){
        articlesInCourseRepository.save(aId, cId);
    }

    @Transactional
    public void delete(int aId, int cId){
        articlesInCourseRepository.delete(cId, aId);
    }

}
