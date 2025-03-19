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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/admin/student/info")
    public String studentInfo(Model model, @RequestParam("id") Long id) {
        User user =  userService.getCurrentUser();
        model.addAttribute("user", user);
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("title", student.getFirstName() + " " + student.getLastName());
        return "studentInfo";
    }

    @GetMapping("/admin/change/status")
    public String changeStatus(Model model, @RequestParam("id") Long id, @RequestParam("status") Integer status) {
        studentService.changeStudentStatus(id, status);
        return "redirect:/admin/student/info?id=" + id;
    }

    @GetMapping("/error")
    public String error(Model model) {
        var user =  userService.getCurrentUser();
        model.addAttribute("user", user);
        return "admin";
    }
}
