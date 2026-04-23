package com.valentin_prevot.invoice_app.controllers; // <-- C'est cette ligne qui manquait !

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Renvoie vers templates/login.html
    }
}