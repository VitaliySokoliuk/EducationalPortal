package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.ArticleService;
import ua.lviv.EduPortal.Services.CourseService;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
            request.setAttribute("courses", courseService.findCoursesInUserList(userId));
            request.setAttribute("articles", articleService.findArticlesInUserList(userId));
        }
        return "myMaterials/myMaterials";
    }

}
