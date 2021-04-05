package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.Topic;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Entities.UserArticle;
import ua.lviv.EduPortal.Services.ArticleService;
import ua.lviv.EduPortal.Services.CourseService;
import ua.lviv.EduPortal.Services.TopicService;
import ua.lviv.EduPortal.Services.UserArticleService;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("allMaterials")
public class AllMaterialsController {

    private ArticleService articleService;
    private CourseService courseService;
    private TopicService topicService;
    private UserArticleService userArticleService;

    @Autowired
    public AllMaterialsController(ArticleService articleService, CourseService courseService,
                                  TopicService topicService, UserArticleService userArticleService) {
        this.articleService = articleService;
        this.courseService = courseService;
        this.topicService = topicService;
        this.userArticleService = userArticleService;
    }

    @GetMapping
    public String allMaterials(HttpServletRequest request,
                               @RequestParam(required = false, name = "topic") String topicName,
                               @RequestParam(required = false) String title){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            request.setAttribute("isUserPresent", true);
        }else{
            request.setAttribute("isUserPresent", false);
        }
        if((topicName != null && !topicName.equals("")) && (title != null && !title.equals(""))){
            request.setAttribute("courses", courseService.findAllByTopicAndTitle(topicName, title));
            request.setAttribute("articles", articleService.findAllByTopicAndTitle(topicName, title));
            request.setAttribute("topicName", topicName);
            request.setAttribute("title", title);
        } else if((topicName == null || topicName.equals("")) && (title != null && !title.equals(""))){
            request.setAttribute("courses", courseService.findAllByTitle(title));
            request.setAttribute("articles", articleService.findAllByTitle(title));
            request.setAttribute("title", title);
        } else if((topicName != null && !topicName.equals(""))){
            request.setAttribute("courses", courseService.findAllByTopic(topicName));
            request.setAttribute("articles", articleService.findAllByTopic(topicName));
            request.setAttribute("topicName", topicName);
        }else{
            request.setAttribute("courses", courseService.findAll());
            request.setAttribute("articles", articleService.findAll());
        }
        List<Topic> topics = topicService.findAll();
        for (int i = 0; i < topics.size(); i++) {
            if(topics.get(i).getName().equals(topicName)){
                topics.remove(i);
                break;
            }
        }
        request.setAttribute("topics", topics);
        return "allMaterials/allMaterials";
    }

    @GetMapping("articleDetails")
    public String articleDetails(HttpServletRequest request, @RequestParam(name = "id") int articleId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        Article article = articleService.findById(articleId);
        request.setAttribute("article", article);
        request.setAttribute("author", article.getAuthor());
        if(currentUser.isPresent()){
            Optional<UserArticle> userArticle = userArticleService.findByUserIdAndArticleId(articleId, currentUser.get().getId());
            if(userArticle.isPresent()){
                request.setAttribute("isAbleToSee", true);
            } else {
                request.setAttribute("isAbleToSee", false);
            }
        } else {
            request.setAttribute("isAbleToSee", false);
        }
        return "allMaterials/articleDetails";
    }

}
