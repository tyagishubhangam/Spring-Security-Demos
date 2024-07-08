package in.krishna.SpringSecurity_login.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @RequestMapping("/home")
    public String myPage(){
        return "home";
    }
    @RequestMapping("/profile")
    public String myProfile(){
        return "profile";
    }
    @RequestMapping("/login")
    public String myLogin(){
        return "login";
    }
    @PostMapping("/perform_login")
    @ResponseBody
    public String performLogin(@RequestParam String username, @RequestParam String password){
        System.out.println(username);
        System.out.println(password);
        return "Username: " + username + " \nPassword: " + password;

    }
    @RequestMapping("/admin/home")
    @ResponseBody
    public String adminHome(){
        return "<h1>Admin Home</h1>";
    }
}
