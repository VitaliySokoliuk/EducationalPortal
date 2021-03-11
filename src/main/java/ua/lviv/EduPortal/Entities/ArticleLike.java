package ua.lviv.EduPortal.Entities;

import javax.persistence.*;

@Entity
@Table(name = "article_likes")
public class ArticleLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ArticleLike(Article article, User user) {
        this.article = article;
        this.user = user;
    }

    public ArticleLike() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", article=" + article +
                ", user=" + user +
                '}';
    }
}
