package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.lviv.EduPortal.Entities.Answer;
import ua.lviv.EduPortal.Entities.Article;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.AnswerService;
import ua.lviv.EduPortal.Services.ArticleService;
import ua.lviv.EduPortal.Services.security.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/myMarks")
public class MyMarksController {

    private AnswerService answerService;
    private ArticleService articleService;

    @Autowired
    public MyMarksController(AnswerService answerService, ArticleService articleService) {
        this.answerService = answerService;
        this.articleService = articleService;
    }

    @GetMapping
    public String myMarks(HttpServletRequest request){
        Optional<User> currentUser = CustomUserDetailsService.getCurrentUser();
        if(currentUser.isPresent()){
            List<Answer> answers = answerService.findAllByUserId(currentUser.get().getId());
            Map<Answer, Article> answerArticle = new HashMap<>();
            for (int i = 0; i < answers.size(); i++) {
                Answer answer = answers.get(i);
                answerArticle.put(answer, articleService.findByHometaskId(answer.getHometask().getId()));
            }
            request.setAttribute("answersArticle", answerArticle);
        }
        return "myMarks/myMarks";
    }

}
