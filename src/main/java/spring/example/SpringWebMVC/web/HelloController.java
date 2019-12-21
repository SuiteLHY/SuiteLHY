package spring.example.SpringWebMVC.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("message", "Hello World!");
        // 备注: 返回值作为视图名称
        return "hello";
    }

    @RequestMapping(value = "/redirectPage")
    public String redirectPage() {
        // URL 重定向
        return "redirect:/hello/finalPage";
    }

    @RequestMapping(value = "/finalPage")
    public String finalPage() {
        return "final";
    }

}
