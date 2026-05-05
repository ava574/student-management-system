package com.project.placement.controller;

import com.project.placement.entity.Student;
import com.project.placement.entity.User;
import com.project.placement.service.StudentService;
import com.project.placement.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final StudentService studentService;

    public AuthController(UserService userService,
                          StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    // GET: /placement/auth/login
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // POST: /placement/auth/login
    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          Model model,
                          HttpSession session) {

        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
        session.setAttribute("loggedUser", user);

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/student/dashboard";
        }
    }

    // GET: /placement/auth/register
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // POST: /placement/auth/register
    @PostMapping("/register")
    public String doRegister(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {

        if (userService.emailExists(email)) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }

        // create user
        userService.registerStudentUser(email, password);

        // create basic student profile
        Student s = new Student();
        s.setName(name);
        s.setEmail(email);
        s.setCourse("Not set");
        s.setCgpa(0.0);
        s.setSkills("Not set");
        studentService.saveStudent(s);

        model.addAttribute("msg", "Registration successful. Please login.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
