package com.valentin_prevot.invoice_app.controllers;

import com.valentin_prevot.invoice_app.models.AppUser;
import com.valentin_prevot.invoice_app.models.Customer;
import com.valentin_prevot.invoice_app.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin/clients")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {
            
        int pageSize = 5; 
        Pageable pageable = PageRequest.of(page, pageSize);
        
        Page<Customer> customerPage = customerService.searchAndPaginate(keyword, pageable);

        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", customerPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "admin/customers/list";
    }

    @GetMapping("/nouveau")
    public String showAddForm(Model model) {
        Customer customer = new Customer();
        customer.setAppUser(new AppUser());
        model.addAttribute("customer", customer);
        return "admin/customers/form";
    }

    @PostMapping("/sauvegarder")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        return "redirect:/admin/clients";
    }

    @GetMapping("/editer/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return "redirect:/admin/clients";
        }
        model.addAttribute("customer", customer);
        return "admin/customers/form";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Le client a été supprimé.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Action refusée : Ce client possède des factures dans le système.");
        }
        return "redirect:/admin/clients";
    }
}