package com.valentin_prevot.invoice_app.controllers;

import com.valentin_prevot.invoice_app.models.DashboardStats;
import com.valentin_prevot.invoice_app.repositories.DashboardStatsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") 
public class AdminController {

    private final DashboardStatsRepository dashboardStatsRepository;

    // Injection du repository via le constructeur
    public AdminController(DashboardStatsRepository dashboardStatsRepository) {
        this.dashboardStatsRepository = dashboardStatsRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Tableau de Bord Administrateur");
        
        // Récupération de la ligne unique de statistiques (ID = 1L)
        DashboardStats stats = dashboardStatsRepository.findById(1L).orElse(null);
        model.addAttribute("stats", stats);
        
        return "admin/dashboard"; 
    }
}