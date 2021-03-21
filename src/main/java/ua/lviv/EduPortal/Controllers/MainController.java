package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.ArticleService;
import ua.lviv.EduPortal.Services.ArticlesInCourseService;
import ua.lviv.EduPortal.Services.CourseService;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private CourseService courseService;
    private ArticleService articleService;
    private ArticlesInCourseService articlesInCourseService;

    @Autowired
    public MainController(CourseService courseService, ArticleService articleService,
                          ArticlesInCourseService articlesInCourseService) {
        this.courseService = courseService;
        this.articleService = articleService;
        this.articlesInCourseService = articlesInCourseService;
    }

    @GetMapping(value = {"home", ""})
    public String getHome(HttpServletRequest req){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            req.setAttribute("isUserPresent", true);
        }else{
            req.setAttribute("isUserPresent", false);
        }
        req.setAttribute("courses", courseService.findAll());
        req.setAttribute("articles", articleService.findAll());
        return "home/home";
    }

    @GetMapping("downloadCourseLogo/{id}")
    public @ResponseBody
    byte[] downloadCourseLogo(@PathVariable int id) {
        return courseService.getLogoPictureById(id);
    }

    @GetMapping("downloadArticleLogo/{id}")
    public @ResponseBody byte[] downloadArticleLogo(@PathVariable int id) {
        return articleService.getLogoPictureById(id);
    }

    @GetMapping("readArticle")
    public String readArticle(HttpServletRequest request, @RequestParam int id){
        request.setAttribute("article", articleService.findById(id));
        return "cabinet/contentArticle";
    }

    @GetMapping("courseDetails")
    public String courseDetails(HttpServletRequest request, @RequestParam("id") int courseId){
        request.setAttribute("course", courseService.findById(courseId));
        request.setAttribute("courseArticles", articlesInCourseService.findArticlesByCourseId(courseId));
        return "home/courseDetails";
    }

}
