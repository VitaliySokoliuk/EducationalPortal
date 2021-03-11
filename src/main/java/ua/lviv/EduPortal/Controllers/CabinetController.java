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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cabinet")
public class CabinetController {

    private UserService userService;
    private ArticleService articleService;
    private HometaskService hometaskService;
    private TopicService topicService;
    private ChapterService chapterService;

    @Autowired
    public CabinetController(UserService userService, ArticleService articleService,
                             HometaskService hometaskService, TopicService topicService,
                             ChapterService chapterService) {
        this.userService = userService;
        this.articleService = articleService;
        this.hometaskService = hometaskService;
        this.topicService = topicService;
        this.chapterService = chapterService;
    }

    @GetMapping
    public String getUserData(HttpServletRequest request){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            User user = currentUser.get();
            request.setAttribute("user", user);
            request.setAttribute("articles", articleService.getByAuthor(user));
            return "cabinet";
        }
        return "403";
    }

    @GetMapping("download_photo")
    public @ResponseBody byte[] downloadPhoto(@RequestParam int id) {
        return userService.getProfilePictureById(id);
    }

    @GetMapping("updateUser")
    public String getUserData2(HttpServletRequest request){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            request.setAttribute("user", currentUser.get());
            return "updateUser";
        }
        return "403";
    }

    @PostMapping("updateUser")
    public String updateUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam MultipartFile photo){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            User user = currentUser.get();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            try {
                String contentType = photo.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    user.setProfilePicture(photo.getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not save file" + photo.getOriginalFilename());
            }
            userService.update(user);
        }
        //ToDo add opportunity to change password
        return "redirect:";
    }

    @GetMapping("createArticle")
    public String createArticle(HttpServletRequest request){
        request.setAttribute("topics", topicService.findAll());
        return "createArticle";
    }

    @PostMapping("createArticle")
    public String createArticle2(@RequestParam String content,
                                 @RequestParam String description,
                                 @RequestParam MultipartFile logo,
                                 @RequestParam String title,
                                 @RequestParam(defaultValue = "true") boolean visibility,
                                 @RequestParam String hometask,
                                 @RequestParam(defaultValue = "false") boolean give_answers,
                                 @RequestParam(defaultValue = "5") int max_point,
                                 @RequestParam(defaultValue = "unavailable") String chapter,
                                 @RequestParam(defaultValue = "unavailable") String topic){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            try {
                Optional<Topic> maybeTopic = topicService.getByName(topic);
                Topic myTopic;
                if(maybeTopic.isEmpty()){
                    myTopic = topicService.save(new Topic(topic));
                }else {
                    myTopic = maybeTopic.get();
                }
                Chapter savedChapter = chapterService.save(new Chapter(myTopic, chapter));
                content = content.replaceAll("&lt;", "<")
                        .replaceAll("&gt;", ">")
                        .replaceAll("&quot;", "\"");
                Article article = new Article(currentUser.get(), title, savedChapter, description,
                                            visibility, false, content);
                String contentType = logo.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    article.setLogoPicture(logo.getBytes());
                }
                hometask = hometask.trim();
                if(!hometask.isEmpty()){
                    Hometask task = new Hometask(hometask, max_point);
                    Hometask savedTask = hometaskService.save(task);
                    article.setGiveAnswers(give_answers);
                    article.setHometask(savedTask);
                }
                articleService.save(article);
            } catch (IOException e) {
                throw new RuntimeException("Could not save file" + logo.getOriginalFilename());
            }
        }
        return "redirect:/cabinet";
    }

    @GetMapping("downloadArticleLogo")
    public @ResponseBody byte[] downloadArticleLogo(@RequestParam int id) {
        return articleService.getLogoPictureById(id);
    }

    @GetMapping("editArticle")
    public String editArticle(HttpServletRequest request, @RequestParam int id){
        List<Topic> topics = topicService.findAll();
        Article article = articleService.findById(id);
        topics.remove(article.getChapter().getTopic());
        request.setAttribute("topics", topics);
        request.setAttribute("article", article);
        return "editArticle";
    }

    @PostMapping("editArticle")
    public String editArticle(@RequestParam String content,
                                 @RequestParam String description,
                                 @RequestParam MultipartFile logo,
                                 @RequestParam String title,
                                 @RequestParam(defaultValue = "true") boolean visibility,
                                 @RequestParam String hometask,
                                 @RequestParam(defaultValue = "false") boolean give_answers,
                                 @RequestParam(defaultValue = "5") int max_point,
                                 @RequestParam(defaultValue = "unavailable") String chapter,
                                 @RequestParam(defaultValue = "unavailable") String topic,
                                 @RequestParam int id){
        try {
            Optional<Topic> maybeTopic = topicService.getByName(topic);
            Topic myTopic;
            if(maybeTopic.isEmpty()){
                myTopic = topicService.save(new Topic(topic));
            }else {
                myTopic = maybeTopic.get();
            }
            Chapter savedChapter = chapterService.save(new Chapter(myTopic, chapter));
            Article article = articleService.findById(id);
            article.setTitle(title);
            article.setChapter(savedChapter);
            article.setDescription(description);
            article.setVisibility(visibility);
            content = content.replaceAll("&lt;", "<")
                    .replaceAll("&gt;", ">")
                    .replaceAll("&quot;", "\"");
            article.setContent(content);
            String contentType = logo.getContentType();
            if (contentType != null && contentType.startsWith("image")) {
                article.setLogoPicture(logo.getBytes());
            }
            hometask = hometask.trim();
            if(!hometask.isEmpty()){
                Hometask task = new Hometask(hometask, max_point);
                if(article.getHometask() != null){
                    task.setId(article.getHometask().getId());
                }
                article.setHometask(hometaskService.save(task));
                article.setGiveAnswers(give_answers);
            }else {
                if(article.getHometask() != null){
                    int htId = article.getHometask().getId();
                    article.setHometask(null);
                    hometaskService.deleteById(htId);
                }
                article.setGiveAnswers(false);
            }
            articleService.save(article);
        } catch (IOException e) {
            throw new RuntimeException("Could not save file" + logo.getOriginalFilename());
        }
        return "redirect:/cabinet";
    }

}
