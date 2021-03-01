package ua.lviv.EduPortal.entities;

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
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean visibility;
    @Lob
    @Column(name = "logo_picture")
    private byte[] logoPicture;

    public Course(User author, String name, Topic topic, String description, boolean visibility, byte[] logoPicture) {
        this.author = author;
        this.name = name;
        this.topic = topic;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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
                ", topic=" + topic +
                ", visibility=" + visibility +
                ", description='" + description + '\'' +
                '}';
    }
}
