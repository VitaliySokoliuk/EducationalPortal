package ua.lviv.EduPortal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home", "/registration", "/resources/**", "/courseDetails",
                            "/downloadCourseLogo/**", "/downloadArticleLogo/**", "/readArticle", "/allMaterials/**").permitAll()
                    .antMatchers("/adminPanel").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .antMatchers("/adminPanel/subjects/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .antMatchers("/adminPanel/allAdmins/**").hasRole("SUPER_ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login").loginProcessingUrl("/spring_security_check")
                    .usernameParameter("email").passwordParameter("password")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/")
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
                .and()
                    .csrf();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
