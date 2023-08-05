package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        //data를 hello라고 넘길것이다 key,value
        //spring model을 하나 만들어서 넣어준다
        //key:data / value:hello!
        return "hello";
        //hello.html을 찾아서 렌더링 해라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

//    @GetMapping("hello-string")
//    @RequestBody
//    public String helloString(@RequestParam("name") String name){
//        return "hello" + name;
//    }


}
