package ua.lviv.EduPortal.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/home")
    public String getHome(HttpServletRequest req){
        return "home";
    }

}
