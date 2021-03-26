package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.ArticleService;
import ua.lviv.EduPortal.Services.CourseService;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/myMaterials")
public class MyMaterialsController {

    private ArticleService articleService;
    private CourseService courseService;

    @Autowired
    public MyMaterialsController(ArticleService articleService, CourseService courseService) {
        this.articleService = articleService;
        this.courseService = courseService;
    }

    @GetMapping
    public String myMaterials(HttpServletRequest request){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if (currentUser.isPresent()){
            int userId = currentUser.get().getId();
            request.setAttribute("coursesAddedByAuthor", courseService.findCoursesInUserList(userId, true));
            request.setAttribute("coursesAddedByUser", courseService.findCoursesInUserList(userId, false));
            request.setAttribute("articlesAddedByAuthor", articleService.findArticlesInUserList(userId, true));
            request.setAttribute("articlesAddedByUser", articleService.findArticlesInUserList(userId, false));
        }
        return "myMaterials/myMaterials";
    }

}
