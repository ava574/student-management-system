package com.project.placement.controller;

import com.project.placement.entity.Company;
import com.project.placement.service.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    private boolean notAdmin(HttpSession session) {
        return session.getAttribute("loggedUser") == null;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword,
                       Model model,
                       HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        model.addAttribute("companies", service.getAllCompanies(keyword));
        model.addAttribute("keyword", keyword);
        return "companies-list";
    }

    @GetMapping("/new")
    public String form(Model model, HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";
        model.addAttribute("company", new Company());
        return "company-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Company company,
                       RedirectAttributes ra,
                       HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        service.saveCompany(company);
        ra.addFlashAttribute("msg", "Company saved successfully!");
        return "redirect:/admin/companies";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model,
                       HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        model.addAttribute("company", service.getCompanyById(id));
        return "company-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes ra,
                         HttpSession session) {
        if (notAdmin(session)) return "redirect:/auth/login";

        service.deleteCompany(id);
        ra.addFlashAttribute("msg", "Company deleted successfully!");
        return "redirect:/admin/companies";
    }
}
