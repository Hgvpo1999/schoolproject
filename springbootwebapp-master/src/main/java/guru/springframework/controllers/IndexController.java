package guru.springframework.controllers;

import guru.springframework.domain.Student;
import guru.springframework.domain.Teacher;
import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class IndexController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/login?logout";
    }
    @GetMapping("/home")
    public String home(Principal principal,Authentication authentication){
        String username = authentication.getName();
        if(authentication.getAuthorities().toString().equals("[ROLE_STUDENT]")){
            return "redirect:/student/"+username;
        } else if (authentication.getAuthorities().toString().equals("[ROLE_TEACHER]")){
            return "redirect:/teacher/"+username;
        } else if (authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")){
            return "redirect:/students";
        }
        return "redirect:/";
    }
}
