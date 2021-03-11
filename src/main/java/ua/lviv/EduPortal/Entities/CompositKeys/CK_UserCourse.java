package ua.lviv.EduPortal.Entities.CompositKeys;

import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CK_UserCourse implements Serializable {

    private User user;
    private Course course;

}