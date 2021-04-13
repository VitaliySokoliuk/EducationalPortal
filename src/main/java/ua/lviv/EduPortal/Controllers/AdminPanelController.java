package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.lviv.EduPortal.Entities.Topic;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Entities.enums.UserRole;
import ua.lviv.EduPortal.Services.ChapterService;
import ua.lviv.EduPortal.Services.TopicService;
import ua.lviv.EduPortal.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adminPanel")
public class AdminPanelController {

    private ChapterService chapterService;
    private TopicService topicService;
    private UserService userService;

    @Autowired
    public AdminPanelController(ChapterService chapterService, TopicService topicService,
                                UserService userService) {
        this.chapterService = chapterService;
        this.topicService = topicService;
        this.userService = userService;
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

}
