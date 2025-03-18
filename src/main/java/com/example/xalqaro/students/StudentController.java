package com.example.xalqaro.students;

import com.example.xalqaro.direction.Direction;
import com.example.xalqaro.direction.DirectionService;
import com.example.xalqaro.user.User;
import com.example.xalqaro.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;
    private final DirectionService directionService;

    @GetMapping("/")
    public String index(Model model) {
        StudentDTO studentDTO = new StudentDTO();
        model.addAttribute("studentDTO", studentDTO);
        List<Direction> directions = directionService.findAll();
        model.addAttribute("directions", directions);
        return "index";
    }

    @PostMapping("/create/application")
    public String createApplication(@ModelAttribute("studentDTO") @Valid StudentDTO studentDTO, BindingResult bindingResult) {
        if(studentService.existsByEmail(studentDTO.getEmail())) {
            bindingResult.addError(new FieldError("studentDTO", "email", "This email cannot be used"));
        }
        if(studentService.existsByPhone(studentDTO.getPhone())) {
            bindingResult.addError(new FieldError("studentDTO", "phone", "This phone cannot be used"));
        }
        if(bindingResult.hasErrors()) {
            return "index";
        }
        studentService.addStudent(studentDTO);
        return "redirect:/success";
    }
    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }
}
