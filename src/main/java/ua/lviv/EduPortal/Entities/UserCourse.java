package ua.lviv.EduPortal.Entities;

import ua.lviv.EduPortal.Entities.CompositKeys.CK_UserCourse;

import javax.persistence.*;

@Entity
@Table(name = "user_course")
@IdClass(value = CK_UserCourse.class)
public class UserCourse {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(nullable = false)
    private boolean bought;

    public UserCourse(User user, Course course, boolean bought) {
        this.user = user;
        this.course = course;
        this.bought = bought;
    }

    public UserCourse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "user=" + user +
                ", course=" + course +
                ", bought=" + bought +
                '}';
    }
}
