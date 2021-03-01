package ua.lviv.EduPortal.entities;

import javax.persistence.*;

@Entity
@Table(name = "course_likes")
public class CourseLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CourseLike(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    public CourseLike() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CourseLike{" +
                "id=" + id +
                ", course=" + course +
                ", user=" + user +
                '}';
    }
}
