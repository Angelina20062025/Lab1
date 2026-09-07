package ru.cafpin.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Главная страница");
        model.addAttribute("data", "Лабораторная работа N1");
        model.addAttribute("content",
                "Знакомство с Spring Framework");
        return "main";
    }

    @GetMapping("about")
    public String about(@RequestParam(required = false, defaultValue = "Имя автора") String name,
                        Model model) {
        model.addAttribute("title", "Страница автора");
        model.addAttribute("author", name);
        return "about";
    }

    @GetMapping("/form")
    public String mainForm(Model model) {
        model.addAttribute("student", new Student());
        return "main-form";
    }

    @PostMapping("/form")
    public String processForm(@ModelAttribute Student student, Model model) {
        String yearStr = String.valueOf(student.getAdmissionYear());
        String lastTwoDigits = yearStr.substring(yearStr.length() - 2);
        String group = "ПИНз-1" + lastTwoDigits;
        student.setGroup(group);

        String login = "student-" + group.toLowerCase() + "-" + student.getId();
        student.setLogin(login);

        model.addAttribute("student", student);
        return "result";
    }
}