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
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;
    @Column(nullable = false, length = 60)
    private String description;
    @Column(nullable = false)
    private boolean visibility;
    @Lob
    @Column(name = "logo_picture")
    private byte[] logoPicture;

    public Course(User author, String name, Chapter chapter, String description, boolean visibility, byte[] logoPicture) {
        this.author = author;
        this.name = name;
        this.chapter = chapter;
        this.description = description;
        this.visibility = visibility;
        this.logoPicture = logoPicture;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
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
                ", name='" + name + '\'' +
                ", chapter=" + chapter +
                ", visibility=" + visibility +
                ", description='" + description + '\'' +
                '}';
    }
}
