package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.Course;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.ArticleLikeService;
import ua.lviv.EduPortal.Services.ArticleService;
import ua.lviv.EduPortal.Services.CourseLikeService;
import ua.lviv.EduPortal.Services.CourseService;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/myMaterials")
public class MyMaterialsController {

    private ArticleService articleService;
    private CourseService courseService;
    private ArticleLikeService articleLikeService;
    private CourseLikeService courseLikeService;

    @Autowired
    public MyMaterialsController(ArticleService articleService, CourseService courseService,
                                 ArticleLikeService articleLikeService, CourseLikeService courseLikeService) {
        this.articleService = articleService;
        this.courseService = courseService;
        this.articleLikeService = articleLikeService;
        this.courseLikeService = courseLikeService;
    }

    @GetMapping
    public String myMaterials(HttpServletRequest request){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if (currentUser.isPresent()){
            int userId = currentUser.get().getId();
            request.setAttribute("coursesUserBought", courseService.findCoursesAndLikesAndPaid(userId, true));
            request.setAttribute("coursesAddedByUser", courseService.findCoursesAndLikesAndPaid(userId, false));
            request.setAttribute("articlesUserBought", articleService.findArticlesAndLikesAndPaid(userId, true));
            request.setAttribute("articlesAddedByUser", articleService.findArticlesAndLikesAndPaid(userId, false));
        }
        return "myMaterials/myMaterials";
    }

    @GetMapping("likeA")
    public String likeArticle(@RequestParam int articleId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            Article article = articleService.findById(articleId);
            User user = currentUser.get();
            articleLikeService.saveOrDelete(user, article);
        }
        return "redirect:/myMaterials";
    }

    @GetMapping("likeC")
    public String likeCourse(@RequestParam int courseId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            Course course = courseService.findById(courseId);
            User user = currentUser.get();
            courseLikeService.saveOrDelete(user, course);
        }
        return "redirect:/myMaterials";
    }
}
