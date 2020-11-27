package ua.lviv.EduPortal.entities;

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
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    @Column(nullable = false)
    private boolean visibility;
    @Column(nullable = false)
    @Type(type = "text")
    private String content;
    @Lob
    @Column(name = "logo_picture")
    private byte[] logoPicture;

    public Article(User author, String name, Topic topic, boolean visibility, String content, byte[] logoPicture) {
        this.author = author;
        this.name = name;
        this.topic = topic;
        this.visibility = visibility;
        this.content = content;
        this.logoPicture = logoPicture;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
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

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", topic=" + topic +
                ", visibility=" + visibility +
                ", content='" + content + '\'' +
                '}';
    }
}
