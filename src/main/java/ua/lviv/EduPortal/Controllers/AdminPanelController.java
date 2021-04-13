package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.lviv.EduPortal.Entities.*;
import ua.lviv.EduPortal.Entities.enums.UserRole;
import ua.lviv.EduPortal.Services.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adminPanel")
public class AdminPanelController {

    private ChapterService chapterService;
    private TopicService topicService;
    private UserService userService;
    private CourseService courseService;
    private ArticleService articleService;
    private UserCourseService userCourseService;
    private UserArticleService userArticleService;
    private CourseLikeService courseLikeService;
    private ArticlesInCourseService articlesInCourseService;
    private ArticleLikeService articleLikeService;
    private AnswerService answerService;
    private HometaskService hometaskService;
    private AnswerFileService answerFileService;

    @Autowired
    public AdminPanelController(ChapterService chapterService, TopicService topicService,
                                UserService userService, CourseService courseService,
                                ArticleService articleService, UserCourseService userCourseService,
                                UserArticleService userArticleService, CourseLikeService courseLikeService,
                                ArticlesInCourseService articlesInCourseService, AnswerService answerService,
                                ArticleLikeService articleLikeService, HometaskService hometaskService,
                                AnswerFileService answerFileService) {
        this.chapterService = chapterService;
        this.topicService = topicService;
        this.userService = userService;
        this.courseService = courseService;
        this.articleService = articleService;
        this.userCourseService = userCourseService;
        this.userArticleService = userArticleService;
        this.courseLikeService = courseLikeService;
        this.articlesInCourseService = articlesInCourseService;
        this.articleLikeService = articleLikeService;
        this.answerService = answerService;
        this.hometaskService = hometaskService;
        this.answerFileService = answerFileService;
    }

    @GetMapping("subjects")
    public String getSubjects(HttpServletRequest request){
        request.setAttribute("topics", topicService.findAll());
        request.setAttribute("chapters", chapterService.findAll());
        return "admin/subjects";
    }

    @GetMapping("deleteT/{id}")
    public String deleteTopic(@PathVariable int id){
        try{
            topicService.deleteById(id);
        }catch (DataIntegrityViolationException e){
            System.out.println(e);
        }
        return "redirect:/adminPanel/subjects";
    }

    @GetMapping("deleteC/{id}")
    public String deleteChapter(@PathVariable int id){
        try{
            chapterService.deleteById(id);
        }catch (DataIntegrityViolationException e){
            System.out.println(e);
        }
        return "redirect:/adminPanel/subjects";
    }

    @PostMapping("addTopic")
    public String addTopic(@RequestParam String name){
        name = name.trim();
        Optional<Topic> maybeTopic = topicService.getByName(name);
        if(maybeTopic.isEmpty() && !name.isEmpty()) {
            topicService.save(new Topic(name));
        }
        return "redirect:/adminPanel/subjects";
    }

    @GetMapping("allAdmins")
    public String allAdmins(HttpServletRequest request){
        List<User> admins = userService.findAllByRole(UserRole.ROLE_ADMIN);
        List<User> superAdmins = userService.findAllByRole(UserRole.ROLE_SUPER_ADMIN);
        request.setAttribute("admins", admins);
        request.setAttribute("superAdmins", superAdmins);
        return "admin/allAdmins";
    }

    @GetMapping("allAdmins/makeUser")
    public String makeUser(@RequestParam int adminId){
        Optional<User> maybeAdmin = userService.findById(adminId);
        if(maybeAdmin.isPresent()){
            User user = maybeAdmin.get();
            user.setRole(UserRole.ROLE_USER);
            userService.update(user);
        }
        return "redirect:/adminPanel/allAdmins";
    }

    @PostMapping("allAdmins")
    public String addAdmin(@RequestParam String email){
        Optional<User> maybeUser = userService.findByEmail(email);
        if(maybeUser.isPresent()){
            User user = maybeUser.get();
            user.setRole(UserRole.ROLE_ADMIN);
            userService.update(user);
        }
        return "redirect:/adminPanel/allAdmins";
    }

    @GetMapping("blockUser")
    public String blockUser(HttpServletRequest request, @RequestParam(required = false) String email){
        List<User> lockedUsers = userService.findAllByNonLockedFalse();
        request.setAttribute("lockedUsers", lockedUsers);
        if(email == null || email.equals("")){
            return "admin/blockUser";
        }
        Optional<User> maybeUser = userService.findByEmail(email);
        if (maybeUser.isPresent()){
            request.setAttribute("user", maybeUser.get());
        }
        return "admin/blockUser";
    }

    @GetMapping("blockUser/block")
    public String blockUserById(@RequestParam int userId){
        Optional<User> maybeUser = userService.findById(userId);
        if (maybeUser.isPresent()){
            User user = maybeUser.get();
            if(user.getRole().equals(UserRole.ROLE_USER)){
                user.setNonLocked(false);
                userService.update(user);
            }
        }
        return "redirect:/adminPanel/blockUser";
    }

    @GetMapping("blockUser/unblock")
    public String unblockUserById(@RequestParam int userId){
        Optional<User> maybeUser = userService.findById(userId);
        if (maybeUser.isPresent()){
            User user = maybeUser.get();
            user.setNonLocked(true);
            userService.update(user);
        }
        return "redirect:/adminPanel/blockUser";
    }

    @GetMapping("accessToMaterials")
    public String accessToMaterials(HttpServletRequest request, @RequestParam(required = false) String email){
        if(email == null || email.equals("")){
            return "admin/accessToMaterials";
        }
        Optional<User> maybeUser = userService.findByEmail(email);
        if (maybeUser.isPresent()){
            User user = maybeUser.get();
            request.setAttribute("user", user);
            List<Course> courses = courseService.findCoursesInUserList(user.getId(), true);
            List<Article> articles = articleService.findArticlesInUserList(user.getId(), true);
            request.setAttribute("courses", courses);
            request.setAttribute("articles", articles);
        }
        return "admin/accessToMaterials";
    }

    @GetMapping("accessToMaterials/delCourse")
    public String delCourse(@RequestParam int courseId, @RequestParam int userId){
        Optional<User> maybeUser = userService.findById(userId);
        if(maybeUser.isPresent()){
            User user = maybeUser.get();
            userCourseService.delete(courseId, userId);
            return "redirect:/adminPanel/accessToMaterials?email=" + user.getEmail();
        }
        return "redirect:/adminPanel/accessToMaterials";
    }

    @GetMapping("accessToMaterials/delArticle")
    public String delArticle(@RequestParam int articleId, @RequestParam int userId){
        Optional<User> maybeUser = userService.findById(userId);
        if(maybeUser.isPresent()){
            User user = maybeUser.get();
            userArticleService.delete(articleId, userId);
            return "redirect:/adminPanel/accessToMaterials?email=" + user.getEmail();
        }
        return "redirect:/adminPanel/accessToMaterials";
    }

    @GetMapping("deleteMaterials")
    public String deleteMaterials(HttpServletRequest request, @RequestParam(required = false) String search){
        if(search == null || search.equals("")){
            return "admin/deleteMaterials";
        }
        request.setAttribute("courses", courseService.findAllByTitle(search));
        request.setAttribute("articles", articleService.findAllByTitle(search));
        request.setAttribute("search", search);
        return "admin/deleteMaterials";
    }

    @GetMapping("deleteMaterials/delCourse")
    public String delCourse(@RequestParam int courseId, @RequestParam String search){
        Course course = courseService.findById(courseId);
        userCourseService.deleteAllByCourseId(courseId);
        courseLikeService.deleteAllByCourseId(courseId);
        articlesInCourseService.deleteAllByCourseId(courseId);
        courseService.deleteById(courseId);
        chapterService.deleteById(course.getChapter().getId());
        return "redirect:/adminPanel/deleteMaterials?search=" + search;
    }

    @GetMapping("deleteMaterials/delArticle")
    public String delArticle(@RequestParam int articleId, @RequestParam String search){
        Article article = articleService.findById(articleId);
        userArticleService.deleteAllByArticleId(articleId);
        articleLikeService.deleteAllByArticleId(articleId);
        articlesInCourseService.deleteAllByArticleId(articleId);

        if(article.getHometask() != null){
            int hometaskId = article.getHometask().getId();
            List<Answer> allAnswersByHometaskId = answerService.findAllByHometaskId(hometaskId);
            answerService.deleteByHometaskId(hometaskId);
            for (Answer a : allAnswersByHometaskId) {
                if(a.getAnswerFile() != null){
                    answerFileService.deleteById(a.getAnswerFile().getId());
                }
            }
            articleService.deleteById(articleId);
            hometaskService.deleteById(hometaskId);
        }else{
            articleService.deleteById(articleId);
        }
        chapterService.deleteById(article.getChapter().getId());
        return "redirect:/adminPanel/deleteMaterials?search=" + search;
    }

}
