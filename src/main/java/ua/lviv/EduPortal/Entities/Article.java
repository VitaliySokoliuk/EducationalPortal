package ua.lviv.EduPortal.Entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hometask_id", referencedColumnName = "id")
    private Hometask hometask;
    @Column(nullable = false, length = 30)
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;
    @Column(nullable = false, length = 120)
    private String description;
    @Column(nullable = false)
    private boolean paid;
    private double price;
    @Column(name = "give_answers", nullable = false)
    private boolean giveAnswers;
    @Column(nullable = false)
    @Type(type = "text")
    private String content;
    @Lob
    @Column(name = "logo_picture")
    private byte[] logoPicture;

    public Article(User author, String title, Chapter chapter, String description,
                   boolean paid, boolean giveAnswers, String content) {
        this.author = author;
        this.title = title;
        this.chapter = chapter;
        this.description = description;
        this.paid = paid;
        this.giveAnswers = giveAnswers;
        this.content = content;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isGiveAnswers() {
        return giveAnswers;
    }

    public void setGiveAnswers(boolean giveAnswers) {
        this.giveAnswers = giveAnswers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getLogoPicture() {
        return logoPicture;
    }

    public void setLogoPicture(byte[] logoPicture) {
        this.logoPicture = logoPicture;
    }

    public Hometask getHometask() {
        return hometask;
    }

    public void setHometask(Hometask hometask) {
        this.hometask = hometask;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", chapter=" + chapter +
                ", description=" + description +
                ", paid=" + paid +
                ", giveAnswers=" + giveAnswers +
                ", content='" + content + '\'' +
                '}';
    }
}
