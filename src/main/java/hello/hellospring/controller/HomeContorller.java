package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContorller {

    //localhost:8080 들어오면 바로 호출 됨
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
