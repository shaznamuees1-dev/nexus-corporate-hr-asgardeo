package com.example.nexus_corporate_hr_asgardeo;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
public String dashboard(@AuthenticationPrincipal OidcUser user, Model model) {
    if (user != null) {
        String name = user.getFullName();
         
        if (name == null || name.isBlank()) {
            name = user.getClaimAsString("given_name");
        }
        
        if (name == null || name.isBlank()) {
            name = user.getClaimAsString("username");
        }
        
        if (name == null || name.isBlank()) {
            name = user.getName(); 
        }

        model.addAttribute("name", name);
        model.addAttribute("email", user.getEmail());
    }
    return "dashboard";
}
}
