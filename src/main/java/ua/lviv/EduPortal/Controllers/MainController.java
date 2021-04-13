package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.lviv.EduPortal.Entities.*;
import ua.lviv.EduPortal.Services.*;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Controller
public class MainController {

    private CourseService courseService;
    private ArticleService articleService;
    private ArticlesInCourseService articlesInCourseService;
    private UserArticleService userArticleService;
    private UserCourseService userCourseService;
    private HometaskService hometaskService;
    private AnswerService answerService;
    private AnswerFileService answerFileService;

    @Autowired
    public MainController(CourseService courseService, ArticleService articleService,
                          ArticlesInCourseService articlesInCourseService, UserArticleService userArticleService,
                          UserCourseService userCourseService, HometaskService hometaskService,
                          AnswerService answerService, AnswerFileService answerFileService) {
        this.courseService = courseService;
        this.articleService = articleService;
        this.articlesInCourseService = articlesInCourseService;
        this.userArticleService = userArticleService;
        this.userCourseService = userCourseService;
        this.hometaskService = hometaskService;
        this.answerService = answerService;
        this.answerFileService = answerFileService;
    }

    @GetMapping(value = {"home", ""})
    public String getHome(HttpServletRequest req){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            req.setAttribute("isUserPresent", true);
        }else{
            req.setAttribute("isUserPresent", false);
        }
        req.setAttribute("courses", courseService.findFewByLikes(3));
        req.setAttribute("articles", articleService.findFewByLikes(3));
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
        request.setAttribute("answerAbility", true);
        return "cabinet/contentArticle";
    }

    @GetMapping("courseDetails")
    public String courseDetails(HttpServletRequest request, @RequestParam("id") int courseId){
        Course course = courseService.findById(courseId);
        if(!course.isPaid()){
            request.setAttribute("isAbleToSee", true);
        }else {
            Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
            if(currentUser.isPresent()){
                Optional<UserCourse> userCourse = userCourseService.findByUserIdAndCourseId(courseId, currentUser.get().getId());
                if(userCourse.isPresent()){
                    request.setAttribute("isAbleToSee", true);
                }else {
                    request.setAttribute("isAbleToSee", false);
                }
            }else {
                request.setAttribute("isAbleToSee", false);
            }
        }
        request.setAttribute("course", course);
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

    @PostMapping("readArticle")
    public String readArticle(@RequestParam(required = false) MultipartFile file,
                              @RequestParam int hometaskId,
                              @RequestParam(required = false) String response){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        Optional<Hometask> maybeHometask = hometaskService.findById(hometaskId);
        if(maybeHometask.isEmpty() || currentUser.isEmpty()){
            return "redirect:/myMaterials";
        }
        if(file.isEmpty() && response.equals("")){
            return "redirect:/myMaterials";
        }
        Hometask hometask = maybeHometask.get();
        User user = currentUser.get();

        Answer answer = new Answer(hometask, user);
        answer.setResponse(response);
        if(!file.isEmpty()){
            try{
                String originalFilename = file.getOriginalFilename();
                AnswerFile answerFile = new AnswerFile(originalFilename, file.getContentType(), file.getBytes());
                AnswerFile savedFile = answerFileService.save(answerFile);
                answer.setAnswerFile(savedFile);
            } catch (IOException e) {
                throw new RuntimeException("Could not save file" + file.getOriginalFilename());
            }
        }
        answerService.save(answer);
        return "redirect:/myMaterials";
    }

    @GetMapping("buyCourse")
    public String buyCourse(HttpServletRequest request, @RequestParam("id") int courseId){
        request.setAttribute("courseId", courseId);
        return "home/buyCourse";
    }

    @PostMapping("buyCourse")
    public String buyCourse(@RequestParam int courseId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            int userId = currentUser.get().getId();
            userCourseService.save(courseId, userId, true);
        }
        return "redirect:/";
    }

    @GetMapping("buyArticle")
    public String buyArticle(HttpServletRequest request, @RequestParam("id") int articleId){
        request.setAttribute("articleId", articleId);
        return "home/buyArticle";
    }

    @PostMapping("buyArticle")
    public String buyArticle(@RequestParam int articleId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            int userId = currentUser.get().getId();
            userArticleService.save(articleId, userId, true);
        }
        return "redirect:/";
    }

}
