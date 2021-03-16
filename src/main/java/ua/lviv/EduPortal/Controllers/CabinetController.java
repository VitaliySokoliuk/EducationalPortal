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
    private CourseService courseService;
    private ArticlesInCourseService articlesInCourseService;
    private UserArticleService userArticleService;

    @Autowired
    public CabinetController(UserService userService, ArticleService articleService,
                             HometaskService hometaskService, TopicService topicService,
                             ChapterService chapterService, CourseService courseService,
                             ArticlesInCourseService articlesInCourseService,
                             UserArticleService userArticleService) {
        this.userService = userService;
        this.articleService = articleService;
        this.hometaskService = hometaskService;
        this.topicService = topicService;
        this.chapterService = chapterService;
        this.courseService = courseService;
        this.articlesInCourseService = articlesInCourseService;
        this.userArticleService = userArticleService;
    }

    @GetMapping
    public String getUserData(HttpServletRequest request){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            User user = currentUser.get();
            request.setAttribute("user", user);
            request.setAttribute("articles", articleService.getByAuthor(user));
            request.setAttribute("courses", courseService.getByAuthor(user));
            return "cabinet/cabinet";
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
            return "cabinet/updateUser";
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
        return "cabinet/createArticle";
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
                Topic savedTopic = topicService.findOrSave(topic);
                Chapter savedChapter = chapterService.save(new Chapter(savedTopic, chapter));
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
                    if(!visibility) {
                        article.setGiveAnswers(give_answers);
                    }else{
                        article.setGiveAnswers(false);
                    }
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
        return "cabinet/editArticle";
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
            Article article = articleService.findById(id);
            if(!(article.getChapter().getName().equals(chapter) &&
                    article.getChapter().getTopic().getName().equals(topic))){
                Topic savedTopic = topicService.findOrSave(topic);
                Chapter savedChapter = chapterService.save(new Chapter(savedTopic, chapter));
                int chapterId = article.getChapter().getId();
                article.setChapter(savedChapter);
                chapterService.deleteById(chapterId);
            }
            article.setTitle(title);
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
                if(!visibility) {
                    article.setGiveAnswers(give_answers);
                }else{
                    article.setGiveAnswers(false);
                }
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

    @GetMapping("createCourse")
    public String createCourse(HttpServletRequest request){
        request.setAttribute("topics", topicService.findAll());
        return "cabinet/createCourse";
    }

    @PostMapping("createCourse")
    public String createCourse(@RequestParam String description,
                                @RequestParam MultipartFile logo,
                                @RequestParam String title,
                                @RequestParam(defaultValue = "true") boolean visibility,
                                @RequestParam(defaultValue = "unavailable") String chapter,
                                @RequestParam(defaultValue = "unavailable") String topic){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            try {

                Topic savedTopic = topicService.findOrSave(topic);
                Chapter savedChapter = chapterService.save(new Chapter(savedTopic, chapter));
                Course course = new Course(currentUser.get(), title, savedChapter, description, visibility);

                String contentType = logo.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    course.setLogoPicture(logo.getBytes());
                }
                courseService.save(course);
            } catch (IOException e) {
                throw new RuntimeException("Could not save file" + logo.getOriginalFilename());
            }
        }
        return "redirect:/cabinet";
    }

    @GetMapping("downloadCourseLogo")
    public @ResponseBody byte[] downloadCourseLogo(@RequestParam int id) {
        return courseService.getLogoPictureById(id);
    }

    @GetMapping("editCourse")
    public String editCourse(HttpServletRequest request, @RequestParam int id){
        List<Topic> topics = topicService.findAll();
        Course course = courseService.findById(id);
        topics.remove(course.getChapter().getTopic());
        request.setAttribute("topics", topics);
        request.setAttribute("course", course);
        return "cabinet/editCourse";
    }

    @PostMapping("editCourse")
    public String editCourse(@RequestParam String description,
                             @RequestParam MultipartFile logo,
                             @RequestParam String title,
                             @RequestParam(defaultValue = "true") boolean visibility,
                             @RequestParam(defaultValue = "unavailable") String chapter,
                             @RequestParam(defaultValue = "unavailable") String topic,
                             @RequestParam int id){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            try {
                Course course = courseService.findById(id);
                if(!(course.getChapter().getName().equals(chapter) &&
                        course.getChapter().getTopic().getName().equals(topic))){
                    Topic savedTopic = topicService.findOrSave(topic);
                    Chapter savedChapter = chapterService.save(new Chapter(savedTopic, chapter));
                    int chapterId = course.getChapter().getId();
                    course.setChapter(savedChapter);
                    chapterService.deleteById(chapterId);
                }
                course.setDescription(description);
                course.setTitle(title);
                course.setVisibility(visibility);
                String contentType = logo.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    course.setLogoPicture(logo.getBytes());
                }
                courseService.save(course);
            } catch (IOException e) {
                throw new RuntimeException("Could not save file" + logo.getOriginalFilename());
            }
        }
        return "redirect:/cabinet";
    }

    @GetMapping("articlesInCourse")
    public String articlesInCourse(HttpServletRequest request, @RequestParam int id){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            User user = currentUser.get();
            List<Article> articlesByCourseId = articlesInCourseService.findArticlesByCourseId(id);
            List<Article> articlesByAuthor = articleService.getByAuthor(user);
            articlesByAuthor.removeAll(articlesByCourseId);
            request.setAttribute("articlesInCourse", articlesByCourseId);
            request.setAttribute("anotherArticle", articlesByAuthor);
            request.setAttribute("courseId", id);
            return "cabinet/articlesInCourse";
        }
        return "403";
    }

    @GetMapping("contentArticle")
    public String contentArticle(HttpServletRequest request, @RequestParam int id){
        request.setAttribute("article", articleService.findById(id));
        return "cabinet/contentArticle";
    }

    @GetMapping("addArticleToCourse")
    public String addArticleToCourse(@RequestParam int aId, @RequestParam int cId){
        articlesInCourseService.save(aId, cId);
        return "redirect:/cabinet/articlesInCourse?id=" + cId;
    }

    @GetMapping("delArticleFromCourse")
    public String deleteArticleFromCourse(@RequestParam int aId, @RequestParam int cId){
        articlesInCourseService.delete(aId, cId);
        return "redirect:/cabinet/articlesInCourse?id=" + cId;
    }

    @GetMapping("articleReaders")
    public String articleReaders(HttpServletRequest request, @RequestParam("id") int articleId){
        request.setAttribute("users", userArticleService.findAllUsersByArticleId(articleId));
        request.setAttribute("articleId", articleId);
        return "cabinet/articleReaders";
    }

    @PostMapping("addUserArticle")
    public String addTopic(@RequestParam String email, @RequestParam int articleId){
        Optional<User> maybeUser = userService.findByEmail(email);
        if(maybeUser.isPresent()){
            int userId = maybeUser.get().getId();
            userArticleService.save(articleId, userId, true);
        }

        return "redirect:/cabinet/articleReaders?id=" + articleId;
    }

}
