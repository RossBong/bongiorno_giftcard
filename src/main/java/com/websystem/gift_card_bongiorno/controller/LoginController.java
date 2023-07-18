package com.websystem.gift_card_bongiorno.controller;

import com.websystem.gift_card_bongiorno.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    //dopo aver eseguito il login controlliamo se l'utente ha effettivamente il ruolo e lo reindirizziamo all'area riservata
    @RequestMapping("/areaRiservata")
    public String getAccessPage(Authentication authentication){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String ruolo=userDetails.getRuolo();
        if (ruolo.equals("NEGOZIANTE") || ruolo.equals("ADMIN")){

            return "areaRiservata";
        }

        return "Login";


    }
    @RequestMapping("/login/error")
    public String getLoginError(Model model){

        model.addAttribute("error","error" );

        return "Login";


    }
}
