package ua.lviv.EduPortal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/adminPanel").setViewName("admin/adminPanel");
//        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/").setViewName("home");
//        registry.addViewController("/welcome").setViewName("welcome_page");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/admin-page").setViewName("admin_page");
    }
}
