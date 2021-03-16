package ua.lviv.EduPortal.Entities.CompositKeys;

import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.User;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CK_UserArticle implements Serializable {

    private User user;
    private Article article;

}