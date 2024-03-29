package ua.lviv.EduPortal.Entities;

import ua.lviv.EduPortal.Entities.CompositKeys.CK_UserArticle;

import javax.persistence.*;

@Entity
@Table(name = "user_article")
@IdClass(value = CK_UserArticle.class)
public class UserArticle {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @Column(nullable = false)
    private boolean bought;

    public UserArticle(User user, Article article, boolean bought) {
        this.user = user;
        this.article = article;
        this.bought = bought;
    }

    public UserArticle() {
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

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "UserArticle{" +
                "user=" + user +
                ", article=" + article +
                ", bought=" + bought +
                '}';
    }
}
