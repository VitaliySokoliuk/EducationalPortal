package ua.lviv.EduPortal.entities;

import ua.lviv.EduPortal.entities.CompositKeys.CK_UserCourses;

import javax.persistence.*;

@Entity
@Table(name = "user_courses")
@IdClass(value = CK_UserCourses.class)
public class UserCourses {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public UserCourses(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    public UserCourses() {
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

    @Override
    public String toString() {
        return "UserCourses{" +
                "user=" + user +
                ", course=" + course +
                '}';
    }
}
