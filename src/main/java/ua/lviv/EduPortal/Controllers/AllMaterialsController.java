package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.ArticleService;
import ua.lviv.EduPortal.Services.CourseService;
import ua.lviv.EduPortal.Services.TopicService;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("allMaterials")
public class AllMaterialsController {

    private ArticleService articleService;
    private CourseService courseService;
    private TopicService topicService;

    @Autowired
    public AllMaterialsController(ArticleService articleService, CourseService courseService,
                                  TopicService topicService) {
        this.articleService = articleService;
        this.courseService = courseService;
        this.topicService = topicService;
    }

    @GetMapping
    public String allMaterials(HttpServletRequest request,
                               @RequestParam(required = false, name = "topic") String topicName){
        request.setAttribute("topics", topicService.findAll());
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            request.setAttribute("isUserPresent", true);
        }else{
            request.setAttribute("isUserPresent", false);
        }
        if(topicName == null || topicName.equals("")){
            request.setAttribute("courses", courseService.findAllIfNotPrivate());
            request.setAttribute("articles", articleService.findAllIfNotPrivate());
            return "allMaterials/allMaterials";
        }
        request.setAttribute("courses", courseService.findAllByTopicIfNotPrivate(topicName));
        request.setAttribute("articles", articleService.findAllByTopicIfNotPrivate(topicName));
        return "allMaterials/allMaterials";
    }



}
