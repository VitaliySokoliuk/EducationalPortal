package ua.lviv.EduPortal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lviv.EduPortal.Entities.User;
import ua.lviv.EduPortal.Services.UserService;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping("registration")
    public String registration(User user){
        userService.save(user);
        return "login";
    }
}
