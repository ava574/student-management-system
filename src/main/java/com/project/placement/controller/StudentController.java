package com.project.placement.controller;

import com.project.placement.entity.Student;
import com.project.placement.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    private boolean notAdmin(HttpSession session) {
        Object u = session.getAttribute("loggedUser");
        return u == null;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword,
                       Model model,
                       HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        model.addAttribute("students", service.getAllStudents(keyword));
        model.addAttribute("keyword", keyword);
        return "students-list";
    }

    @GetMapping("/new")
    public String form(Model model, HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student,
                       RedirectAttributes ra,
                       HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        service.saveStudent(student);
        ra.addFlashAttribute("msg", "Student saved successfully!");
        return "redirect:/admin/students";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model,
                       HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        model.addAttribute("student", service.getStudentById(id));
        return "student-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes ra,
                         HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        service.deleteStudent(id);
        ra.addFlashAttribute("msg", "Student deleted successfully!");
        return "redirect:/admin/students";
    }
}
