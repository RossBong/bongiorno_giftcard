package com.websystem.gift_card_bongiorno.controller.rest;

import com.websystem.gift_card_bongiorno.model.Carta;
import com.websystem.gift_card_bongiorno.model.Transazione;
import com.websystem.gift_card_bongiorno.service.CartaService;
import com.websystem.gift_card_bongiorno.service.TransazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class GestioneCarteControllerRest {

    @Autowired
    private CartaService cartaService;//service per la gestione dell' entity Carta
    @Autowired
    private TransazioneService transazioneService;//service per la gestione dell' entity Transazione

    @RequestMapping("/admin/setStato/{id}")
    public Carta setStato(@PathVariable int id){

        return cartaService.setStatoById(id);



    }

    @RequestMapping("/admin/newCarta/{credito}")
    public Carta newCarta(@PathVariable float credito){


        return cartaService.newCarta(credito);


    }

    @RequestMapping("/getCarta/{id}")
    public Carta getCarta(@PathVariable int id){

        return cartaService.getById(id);

    }

    @RequestMapping("/newTransazione/{imp}/{ncarta}")
    public Transazione newTransazione(@PathVariable Float imp, @PathVariable Integer ncarta, Authentication authentication){


        return transazioneService.newTransazione(imp,ncarta,authentication);


    }




}
