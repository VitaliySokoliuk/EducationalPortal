package ua.lviv.EduPortal.entities;

import ua.lviv.EduPortal.entities.CompositKeys.CK_UserArticles;

import javax.persistence.*;

@Entity
@Table(name = "user_articles")
@IdClass(value = CK_UserArticles.class)
public class UserArticles {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public UserArticles(User user, Article article) {
        this.user = user;
        this.article = article;
    }

    public UserArticles() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "UserArticles{" +
                "user=" + user +
                ", article=" + article +
                '}';
    }
}
