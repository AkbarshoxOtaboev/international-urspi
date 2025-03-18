package com.example.xalqaro.direction;

import com.example.xalqaro.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class DirectionController {
    private final DirectionService directionService;
    private final UserService userService;
    @GetMapping("/directions")
    public String directions(Model model) {
        var user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("title","Yo`nalish qo`shish");
        List<Direction> directions = directionService.findAll();
        model.addAttribute("directions", directions);
        Direction direction = new Direction();
        model.addAttribute("direction", direction);
        return "directions";
    }

    @PostMapping("/direction/create")
    public String create(Model model, Direction direction) {
        directionService.save(direction);
        return "redirect:/admin/directions";
    }

    @GetMapping("/direction/getOne")
    @ResponseBody
    public Direction getOne(Long id) {
        return directionService.findById(id);
    }

    @PostMapping("/direction/update")
    public String update(Model model, Direction direction) {
        directionService.update(direction, direction.getId());
        return "redirect:/admin/directions";
    }
    @GetMapping("/direction/remove")
    public String delete(@RequestParam("id") Long id,@RequestParam("status") Integer status) {
        directionService.delete(id, status);
        return "redirect:/admin/directions";
    }
}
