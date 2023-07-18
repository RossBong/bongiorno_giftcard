package com.websystem.gift_card_bongiorno.service;

import com.websystem.gift_card_bongiorno.model.Carta;
import com.websystem.gift_card_bongiorno.model.Transazione;
import com.websystem.gift_card_bongiorno.model.Utente;
import com.websystem.gift_card_bongiorno.repository.TransazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.Calendar;


@Service
public class TransazioneService {

    @Autowired
    TransazioneRepository Trepo;
    @Autowired
    private CartaService cartaService;

    public Transazione newTransazione(float importo, Integer idCarta, Authentication authentication){
        Carta carta = cartaService.getById(idCarta);

        Calendar cal = Calendar.getInstance();
        Date dataCorrente = new Date(cal.getTimeInMillis());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Utente utente=userDetails.getUtente();

        Transazione transazione= new Transazione(importo, dataCorrente,utente,carta);

        return Trepo.save(transazione);
        // evento trigger del db gestisce l'aggiornamento dell'attributo credito nella tabella carte ad ogni transazione
    }

    public  Iterable<Transazione> getTransazioni(Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Utente utente = userDetails.getUtente();

        if(utente.getRuolo().equals("ADMIN")){
            return Trepo.findAllOrderByIdDesc();

        }
        else if(utente.getRuolo().equals("NEGOZIANTE")) {
            return Trepo.loadUsingUtenteNativeQuery(utente.getId());

        }

        return null;
    }



}
