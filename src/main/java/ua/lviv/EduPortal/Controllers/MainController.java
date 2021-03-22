package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.*;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class MainController {

    private CourseService courseService;
    private ArticleService articleService;
    private ArticlesInCourseService articlesInCourseService;
    private UserArticleService userArticleService;
    private UserCourseService userCourseService;

    @Autowired
    public MainController(CourseService courseService, ArticleService articleService,
                          ArticlesInCourseService articlesInCourseService, UserArticleService userArticleService,
                          UserCourseService userCourseService) {
        this.courseService = courseService;
        this.articleService = articleService;
        this.articlesInCourseService = articlesInCourseService;
        this.userArticleService = userArticleService;
        this.userCourseService = userCourseService;
    }

    @GetMapping(value = {"home", ""})
    public String getHome(HttpServletRequest req){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            req.setAttribute("isUserPresent", true);
        }else{
            req.setAttribute("isUserPresent", false);
        }
        req.setAttribute("courses", courseService.findAllIfNotPrivate());
        req.setAttribute("articles", articleService.findAllIfNotPrivate());
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

    @GetMapping("addAToList")
    public String addAToList(@RequestParam("id") int articleId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            int userId = currentUser.get().getId();
            userArticleService.save(articleId, userId, false);
        }
        return "redirect:/";
    }

    @GetMapping("addCToList")
    public String addCToList(@RequestParam("id") int courseId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            int userId = currentUser.get().getId();
            userCourseService.save(courseId, userId, false);
        }
        return "redirect:/";
    }

}
