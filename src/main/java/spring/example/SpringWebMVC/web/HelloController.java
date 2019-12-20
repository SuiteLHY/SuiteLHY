package spring.example.SpringWebMVC.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("message", "Hello World!");
        // 备注: 返回值作为视图名称
        return "hello";
    }

}
