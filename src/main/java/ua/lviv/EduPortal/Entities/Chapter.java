package ua.lviv.EduPortal.Entities;

import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(nullable = false, length = 30)
    private String name;

    public Chapter(Topic topic, String name) {
        this.topic = topic;
        this.name = name;
    }

    public Chapter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", topic=" + topic +
                ", name='" + name + '\'' +
                '}';
    }
}
