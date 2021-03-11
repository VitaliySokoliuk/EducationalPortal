package ua.lviv.EduPortal.Entities;

import ua.lviv.EduPortal.Entities.CompositKeys.CK_ArticlesInCourse;

import javax.persistence.*;

@Entity
@Table(name = "articles_in_course")
@IdClass(value = CK_ArticlesInCourse.class)
public class ArticlesInCourse {

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Id
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public ArticlesInCourse(Course course, Article article) {
        this.course = course;
        this.article = article;
    }

    public ArticlesInCourse() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "ArticlesInCourse{" +
                "course=" + course +
                ", article=" + article +
                '}';
    }
}
