package com.project.placement.controller;

import com.project.placement.entity.Student;
import com.project.placement.entity.User;
import com.project.placement.service.CompanyService;
import com.project.placement.service.PlacementService;
import com.project.placement.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final StudentService studentService;
    private final CompanyService companyService;
    private final PlacementService placementService;

    public DashboardController(StudentService studentService,
                               CompanyService companyService,
                               PlacementService placementService) {
        this.studentService = studentService;
        this.companyService = companyService;
        this.placementService = placementService;
    }

    // redirect root based on role
    @GetMapping("/")
    public String home(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            return "redirect:/auth/login";
        }
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/student/dashboard";
        }
    }

    // ADMIN dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/auth/login";
        }

        long totalStudents = studentService.getAllStudents(null).size();
        long totalCompanies = companyService.getAllCompanies(null).size();
        long totalPlacements = placementService.getAllPlacements().size();

        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("totalCompanies", totalCompanies);
        model.addAttribute("totalPlacements", totalPlacements);

        return "admin-dashboard";
    }

    // STUDENT dashboard
    @GetMapping("/student/dashboard")
    public String studentDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null || !"STUDENT".equalsIgnoreCase(user.getRole())) {
            return "redirect:/auth/login";
        }

        Student s = studentService.getByEmail(user.getEmail());
        model.addAttribute("student", s);
        if (s != null) {
            model.addAttribute("placements", placementService.getPlacementsByStudent(s.getId()));
        }
        return "student-dashboard";
    }
}
