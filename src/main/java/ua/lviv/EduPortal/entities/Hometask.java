package ua.lviv.EduPortal.entities;

import javax.persistence.*;

@Entity
@Table(name = "hometask")
public class Hometask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
    @Column(nullable = false)
    private String task;
    @Column(name = "max_mark", nullable = false)
    private int maxMark;

    public Hometask(Article article, String task, int maxMark) {
        this.article = article;
        this.task = task;
        this.maxMark = maxMark;
    }

    public Hometask() {
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }

    @Override
    public String toString() {
        return "Hometask{" +
                "id=" + id +
                ", article=" + article +
                ", task='" + task + '\'' +
                ", maxMark=" + maxMark +
                '}';
    }
}
