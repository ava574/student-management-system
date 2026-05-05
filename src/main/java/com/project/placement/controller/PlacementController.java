package com.project.placement.controller;

import com.project.placement.entity.Placement;
import com.project.placement.service.CompanyService;
import com.project.placement.service.PlacementService;
import com.project.placement.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/placements")
public class PlacementController {

    private final PlacementService placementService;
    private final StudentService studentService;
    private final CompanyService companyService;

    public PlacementController(PlacementService placementService,
                               StudentService studentService,
                               CompanyService companyService) {
        this.placementService = placementService;
        this.studentService = studentService;
        this.companyService = companyService;
    }

    private boolean notAdmin(HttpSession session) {
        return session.getAttribute("loggedUser") == null;
    }

    @GetMapping
    public String list(Model model, HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        model.addAttribute("placements", placementService.getAllPlacements());
        return "placements-list";
    }

    @GetMapping("/new")
    public String form(Model model, HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        model.addAttribute("placement", new Placement());
        model.addAttribute("students", studentService.getAllStudents(null));
        model.addAttribute("companies", companyService.getAllCompanies(null));
        return "placement-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Placement placement,
                       @RequestParam Long studentId,
                       @RequestParam Long companyId,
                       RedirectAttributes ra,
                       HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        placement.setStudent(studentService.getStudentById(studentId));
        placement.setCompany(companyService.getCompanyById(companyId));
        placementService.savePlacement(placement);
        ra.addFlashAttribute("msg", "Placement saved successfully!");
        return "redirect:/admin/placements";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes ra,
                         HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        placementService.deletePlacement(id);
        ra.addFlashAttribute("msg", "Placement deleted successfully!");
        return "redirect:/admin/placements";
    }
}
