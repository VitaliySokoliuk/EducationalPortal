package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.lviv.EduPortal.DTOs.ArticleDto;
import ua.lviv.EduPortal.DTOs.CourseDto;
import ua.lviv.EduPortal.DTOs.UserDto;
import ua.lviv.EduPortal.Entities.*;
import ua.lviv.EduPortal.Services.*;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
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
    private UserCourseService userCourseService;
    private AnswerService answerService;
    private AnswerFileService answerFileService;
    private CourseLikeService courseLikeService;
    private ArticleLikeService articleLikeService;

    @Autowired
    public CabinetController(UserService userService, ArticleService articleService,
                             HometaskService hometaskService, TopicService topicService,
                             ChapterService chapterService, CourseService courseService,
                             ArticlesInCourseService articlesInCourseService,
                             UserArticleService userArticleService, UserCourseService userCourseService,
                             AnswerService answerService, AnswerFileService answerFileService,
                             CourseLikeService courseLikeService, ArticleLikeService articleLikeService) {
        this.userService = userService;
        this.articleService = articleService;
        this.hometaskService = hometaskService;
        this.topicService = topicService;
        this.chapterService = chapterService;
        this.courseService = courseService;
        this.articlesInCourseService = articlesInCourseService;
        this.userArticleService = userArticleService;
        this.userCourseService = userCourseService;
        this.answerService = answerService;
        this.answerFileService = answerFileService;
        this.courseLikeService = courseLikeService;
        this.articleLikeService = articleLikeService;
    }

    @Value("${minUserRating}")
    private int minUserRating;

    @GetMapping
    public String getUserData(HttpServletRequest request){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            User user = currentUser.get();
            int courseLikes = courseLikeService.getLikesByUserId(user.getId());
            int articleLikes = articleLikeService.getLikesByUserId(user.getId());
            int rating = courseLikes + articleLikes;
            if (!user.isPaidMaterials()){
                if (rating > minUserRating){
                    user.setPaidMaterials(true);
                    userService.update(user);
                }
                request.setAttribute("likes", rating + "/" + minUserRating);
            }else{
                request.setAttribute("likes", rating);
            }
            List<ArticleDto> articles = articleService.findAllArticlesAndLikes(user.getId());
            List<CourseDto> courses = courseService.findAllCoursesAndLikes(user.getId());
            request.setAttribute("numOfArticles", articles.size());
            request.setAttribute("numOfCourses", courses.size());
            request.setAttribute("user", user);
            request.setAttribute("articles", articles);
            request.setAttribute("courses", courses);
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
                             @RequestParam(defaultValue = "pass1") String pass1,
                             @RequestParam(defaultValue = "pass2") String pass2,
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
            if (pass1.equals(pass2)) {
                user.setPassword(pass1);
                userService.updateWithPass(user);
            } else {
                userService.update(user);
            }
        }
        return "redirect:";
    }

    @GetMapping("createArticle")
    public String createArticle(HttpServletRequest request){
        request.setAttribute("topics", topicService.findAll());
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if (currentUser.isPresent()){
            request.setAttribute("userIsPaid", currentUser.get().isPaidMaterials());
        }
        return "cabinet/createArticle";
    }

    @PostMapping("createArticle")
    public String createArticle2(@RequestParam String content,
                                 @RequestParam String description,
                                 @RequestParam MultipartFile logo,
                                 @RequestParam String title,
                                 @RequestParam(defaultValue = "false") boolean paid,
                                 @RequestParam(defaultValue = "0.0") double price,
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
                                            paid, false, content);
                String contentType = logo.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    article.setLogoPicture(logo.getBytes());
                }
                if(paid) {
                    article.setPrice(price);
                    article.setGiveAnswers(give_answers);
                }else{
                    article.setGiveAnswers(false);
                }
                hometask = hometask.trim();
                if(!hometask.isEmpty()){
                    Hometask task = new Hometask(hometask, max_point);
                    Hometask savedTask = hometaskService.save(task);
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
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if (currentUser.isPresent()){
            request.setAttribute("userIsPaid", currentUser.get().isPaidMaterials());
        }
        return "cabinet/editArticle";
    }

    @PostMapping("editArticle")
    public String editArticle(@RequestParam String content,
                                 @RequestParam String description,
                                 @RequestParam MultipartFile logo,
                                 @RequestParam String title,
                                 @RequestParam(defaultValue = "false") boolean paid,
                                 @RequestParam(defaultValue = "0.0") double price,
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
            article.setPaid(paid);
            content = content.replaceAll("&lt;", "<")
                    .replaceAll("&gt;", ">")
                    .replaceAll("&quot;", "\"");
            article.setContent(content);
            String contentType = logo.getContentType();
            if (contentType != null && contentType.startsWith("image")) {
                article.setLogoPicture(logo.getBytes());
            }
            hometask = hometask.trim();
            if(paid) {
                article.setPrice(price);
                article.setGiveAnswers(give_answers);
            }else{
                article.setGiveAnswers(false);
            }
            if(!hometask.isEmpty()){
                Hometask task = new Hometask(hometask, max_point);
                if(article.getHometask() != null){
                    task.setId(article.getHometask().getId());
                }
                article.setHometask(hometaskService.save(task));
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
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if (currentUser.isPresent()){
            request.setAttribute("userIsPaid", currentUser.get().isPaidMaterials());
        }
        return "cabinet/createCourse";
    }

    @PostMapping("createCourse")
    public String createCourse(@RequestParam String description,
                                @RequestParam MultipartFile logo,
                                @RequestParam String title,
                                @RequestParam(defaultValue = "false") boolean paid,
                                @RequestParam(defaultValue = "0.0") double price,
                                @RequestParam(defaultValue = "unavailable") String chapter,
                                @RequestParam(defaultValue = "unavailable") String topic){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            try {

                Topic savedTopic = topicService.findOrSave(topic);
                Chapter savedChapter = chapterService.save(new Chapter(savedTopic, chapter));
                Course course = new Course(currentUser.get(), title, savedChapter, description, paid);
                if(paid){
                    course.setPrice(price);
                }
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
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if (currentUser.isPresent()){
            request.setAttribute("userIsPaid", currentUser.get().isPaidMaterials());
        }
        return "cabinet/editCourse";
    }

    @PostMapping("editCourse")
    public String editCourse(@RequestParam String description,
                             @RequestParam MultipartFile logo,
                             @RequestParam String title,
                             @RequestParam boolean paid,
                             @RequestParam(defaultValue = "0.0") double price,
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
                course.setPaid(paid);
                if(paid){
                    course.setPrice(price);
                }
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
    public String articlesInCourse(HttpServletRequest request, @RequestParam(name = "id") int courseId){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            User user = currentUser.get();
            List<Article> articlesByCourseId = articlesInCourseService.findArticlesByCourseId(courseId);
            List<Article> articlesByAuthor = articleService.getByAuthor(user);
            articlesByAuthor.removeAll(articlesByCourseId);
            request.setAttribute("articlesInCourse", articlesByCourseId);
            request.setAttribute("anotherArticle", articlesByAuthor);
            request.setAttribute("courseId", courseId);
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
        List<UserDto> allUsersByArticleId = userArticleService.findAllUsersByArticleId(articleId);
        int readersCount = allUsersByArticleId.size();
        request.setAttribute("users", allUsersByArticleId);
        request.setAttribute("readersCount", readersCount);
        request.setAttribute("articleId", articleId);
        return "cabinet/articleReaders";
    }

    @GetMapping("courseReaders")
    public String courseReaders(HttpServletRequest request, @RequestParam("id") int courseId){
        List<UserDto> allUsersByCourseId = userCourseService.findAllUsersByCourseId(courseId);
        int readersCount = allUsersByCourseId.size();
        request.setAttribute("users", allUsersByCourseId);
        request.setAttribute("readersCount", readersCount);
        request.setAttribute("courseId", courseId);
        return "cabinet/courseReaders";
    }

    @GetMapping("articleAnswers")
    public String articleAnswers(HttpServletRequest request, @RequestParam("id") int articleId){
        int hometastId = articleService.findHTidByArticleId(articleId);
        List<Answer> allByHometaskId = answerService.findAllByHometaskId(hometastId);
        request.setAttribute("answers", allByHometaskId);
        request.setAttribute("articleId", articleId);
        return "cabinet/articleAnswers";
    }

    @GetMapping("downloadAnswerFile")
    public ResponseEntity<byte[]> getFile(@RequestParam int id) {
        AnswerFile answerFile = answerFileService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + answerFile.getName() + "\"")
                .body(answerFile.getData());
    }

    @GetMapping("confirmAnswer/{id}")
    public String confirmAnswer(@PathVariable(name = "id") int answerId, @RequestParam int articleId,
                                @RequestParam double mark, @RequestParam(required = false) String feedback){
        Answer answer = answerService.findById(answerId);
        if(feedback != null && !feedback.equals("")){
            answer.setFeedback(feedback);
        }
        answer.setMark(mark);
        answerService.save(answer);
        return "redirect:/cabinet/articleAnswers?id=" + articleId;
    }

    @GetMapping("allAnswers")
    public String allAnswers(HttpServletRequest request){
        Optional<User> maybeUser = CustomUserDetailsService.getCurrentUser();
        if(maybeUser.isPresent()){
            request.setAttribute("answers", answerService.findAllForAuthor(maybeUser.get().getId()));
        }
        return "cabinet/allAnswers";
    }

    @PostMapping("allAnswers")
    public String allAnswers2(@RequestParam int answerId, @RequestParam double mark,
                              @RequestParam(required = false) String feedback){
        Answer answer = answerService.findById(answerId);
        if(feedback != null && !feedback.equals("")){
            answer.setFeedback(feedback);
        }
        answer.setMark(mark);
        answerService.save(answer);
        return "redirect:/cabinet/allAnswers";
    }

    @GetMapping("deleteArticle")
    public String deleteArticle(@RequestParam int articleId){
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
        return "redirect:/cabinet";
    }

    @GetMapping("deleteCourse")
    public String deleteCourse(@RequestParam int courseId){
        Course course = courseService.findById(courseId);
        userCourseService.deleteAllByCourseId(courseId);
        courseLikeService.deleteAllByCourseId(courseId);
        articlesInCourseService.deleteAllByCourseId(courseId);
        courseService.deleteById(courseId);
        chapterService.deleteById(course.getChapter().getId());
        return "redirect:/cabinet";
    }

}
