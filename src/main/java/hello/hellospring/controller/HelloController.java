package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello~~~~!");
        return "hello";     /*resources의 이 리턴값과 같은 html을 실행시킴*/
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        // hello-mvc?name=넣을문자열 <- 이런식으로 name 이라는 변수명으로 파라미터 넘겨주면
        // html에 ${ } <- 여기에 아래의 어트리뷰트명에 매핑해서 값을 채운다
        model.addAttribute("data", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody                   // http에서 응답 body부에 이 데이터를 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello "+ name;      // "hello spring" <- 문자열 그대로 내려줌
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;           // json 으로 return

    }


    static class Hello{
        private String name;
        //property 접근방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
