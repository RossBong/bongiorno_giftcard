package com.websystem.gift_card_bongiorno.controller;

import com.websystem.gift_card_bongiorno.model.Carta;
import com.websystem.gift_card_bongiorno.model.Transazione;
import com.websystem.gift_card_bongiorno.service.CartaService;
import com.websystem.gift_card_bongiorno.service.TransazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ReportController {

    @Autowired
    private TransazioneService transazioneService;
    @Autowired
    private CartaService cartaService;

    //per quanto riguarda l'ADMIN mostriamo tutte le transazioni, per il NOZIANTE mostriamo solo le transazioni effettuate da lui
    @RequestMapping("/elencoTransazioni")
    public String  getTransazioni(Authentication authentication, Model model) {


        Iterable<Transazione> transazioni = transazioneService.getTransazioni(authentication);
        model.addAttribute("transazioni", transazioni);


        return "elencoTransazioni";
    }


    @RequestMapping("/elencoCarte")
    public String  getCarte(Authentication authentication, Model model) {

        Iterable<Carta> carte = cartaService.getCarte(authentication);
        model.addAttribute("carte", carte);
        return "elencocarte";
    }
}
