package ua.lviv.EduPortal.entities.CompositKeys;

import ua.lviv.EduPortal.entities.Article;
import ua.lviv.EduPortal.entities.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CK_UserArticles implements Serializable {

    private User user;
    private Article article;

}