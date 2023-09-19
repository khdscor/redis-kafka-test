package MyTest.test.domain.article.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class SessionController {

    //HashMap<String, String> sessionMap = new HashMap<>();

    @GetMapping("/login")
    public String login(HttpSession httpSession, @RequestParam String name){
//        sessionMap.put(httpSession.getId(), name);
        httpSession.setAttribute("test", name);
        return "saved";
    }

    @GetMapping("/myName")
    public String myName(HttpSession httpSession){
//        String myName = sessionMap.get(httpSession.getId());

        return (String) httpSession.getAttribute("test");
    }
}
