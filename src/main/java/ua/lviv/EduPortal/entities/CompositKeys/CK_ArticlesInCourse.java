package ua.lviv.EduPortal.entities.CompositKeys;

import ua.lviv.EduPortal.entities.Article;
import ua.lviv.EduPortal.entities.Course;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CK_ArticlesInCourse implements Serializable {

    private Course course;
    private Article article;

}