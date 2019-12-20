package spring.example.SpringWebMVC.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.example.SpringWebMVC.domain.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping(value = "/newStudent")
    public ModelAndView student() {
        return new ModelAndView("student"
                , "studentInfo"
                , new Student(1, "LiMing", 18));
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute Student student) {
        /*model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());*/
        /*model.addAttribute("student", student);*/
        return "result";
    }

}
