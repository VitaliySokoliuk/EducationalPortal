package ua.lviv.EduPortal.entities.CompositKeys;

import ua.lviv.EduPortal.entities.Course;
import ua.lviv.EduPortal.entities.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CK_UserCourses implements Serializable {

    private User user;
    private Course course;

}