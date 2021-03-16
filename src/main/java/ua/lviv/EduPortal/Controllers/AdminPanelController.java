package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.lviv.EduPortal.Entities.Topic;
import ua.lviv.EduPortal.Services.ChapterService;
import ua.lviv.EduPortal.Services.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adminPanel")
public class AdminPanelController {

    private ChapterService chapterService;
    private TopicService topicService;

    @Autowired
    public AdminPanelController(ChapterService chapterService, TopicService topicService) {
        this.chapterService = chapterService;
        this.topicService = topicService;
    }

    @GetMapping("subjects")
    private String getSubjects(HttpServletRequest request){
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

}
