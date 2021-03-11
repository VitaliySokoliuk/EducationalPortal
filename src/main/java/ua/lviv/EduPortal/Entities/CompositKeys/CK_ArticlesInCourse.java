package ua.lviv.EduPortal.Entities.CompositKeys;

import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.Course;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CK_ArticlesInCourse implements Serializable {

    private Course course;
    private Article article;

}