package com.example.xalqaro;

import com.example.xalqaro.students.Student;
import com.example.xalqaro.students.StudentService;
import com.example.xalqaro.user.User;
import com.example.xalqaro.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final StudentService studentService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        User user =  userService.getCurrentUser();
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/admin/students")
    public String students(Model model) {
        User user =  userService.getCurrentUser();
        model.addAttribute("user", user);
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "students";
    }
}
