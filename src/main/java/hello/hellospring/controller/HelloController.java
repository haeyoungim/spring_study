package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



//정적 컨텐츠 : 그냥 파일을 내려준다

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        //data를 hello라고 넘길것이다 key,value
        //spring model을 하나 만들어서 넣어준다
        //key:data / value:hello!
        return "hello";
        //hello.html을 찾아서 렌더링 해라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //http에서 body쪽에 직접 넣어주겠다
    //페이지 소스보기 하면 그대로 나옴
    //json으로 자동으로 반환

    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; //http://localhost:8080/hello-string?name=키키
    }

    @GetMapping("hello-api")
    @ResponseBody
    //리턴값이 객체면 기본을 json 데이터로 만들어서 http에 반환
    //jsonConverter가 동작(string이면 stringConverter)
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();//커맨드+시프트+엔터 = 자동완성
        hello.setName(name);
        return hello;
    }


    static class Hello {
        //객체 선언
        //helloContoller.hello
        //컨트롤+엔터 = 자동완성

        //자바빈 규약 / 자바빈표준 방식
        //프로퍼티 접근 방식
        //라이브러리에서 쓰거나 메서드를 통해서 접근

        private String name;

        //넣을때
        public String getName() {
            return name;
        }

        //꺼낼때
        public void setName(String name) {
            this.name = name;
        }
    }


}
