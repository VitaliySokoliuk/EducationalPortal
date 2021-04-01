package ua.lviv.EduPortal.Entities;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
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
    @Lob
    @Column(name = "logo_picture")
    private byte[] logoPicture;

    public Course(User author, String title, Chapter chapter, String description, boolean paid) {
        this.author = author;
        this.title = title;
        this.chapter = chapter;
        this.description = description;
        this.paid = paid;
    }

    public Course() {
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

    public byte[] getLogoPicture() {
        return logoPicture;
    }

    public void setLogoPicture(byte[] logoPicture) {
        this.logoPicture = logoPicture;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", chapter=" + chapter +
                ", paid=" + paid +
                ", description='" + description + '\'' +
                '}';
    }
}
