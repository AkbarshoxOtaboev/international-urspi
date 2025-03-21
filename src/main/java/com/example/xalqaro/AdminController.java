package com.example.xalqaro;

import com.example.xalqaro.storage.StorageService;
import com.example.xalqaro.students.Student;
import com.example.xalqaro.students.StudentService;
import com.example.xalqaro.students.StudentStatusDTO;
import com.example.xalqaro.user.User;
import com.example.xalqaro.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final StudentService studentService;
    private final StorageService storageService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        User user =  userService.getCurrentUser();
        model.addAttribute("user", user);
        Integer activeCount = studentService.fetchActiveApplication();
        model.addAttribute("activeCount", activeCount);
        StudentStatusDTO counts = studentService.fetchStudentCountStatus();
        model.addAttribute("counts", counts);
        return "admin";
    }

    @GetMapping("/admin/students")
    public String students(Model model) {
        User user =  userService.getCurrentUser();
        model.addAttribute("user", user);
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        Integer activeCount = studentService.fetchActiveApplication();
        model.addAttribute("activeCount", activeCount);
        return "students";
    }

    @GetMapping("/admin/student/info")
    public String studentInfo(Model model, @RequestParam("id") Long id) {
        User user =  userService.getCurrentUser();
        model.addAttribute("user", user);
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("title", student.getFirstName() + " " + student.getLastName());
        Integer activeCount = studentService.fetchActiveApplication();
        model.addAttribute("activeCount", activeCount);

        return "studentInfo";
    }

    @GetMapping("/admin/change/status")
    public String changeStatus(Model model, @RequestParam("id") Long id, @RequestParam("status") Integer status) {
        studentService.changeStudentStatus(id, status);
        return "redirect:/admin/student/info?id=" + id;
    }

    @GetMapping("/admin/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
