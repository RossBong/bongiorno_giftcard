package com.websystem.gift_card_bongiorno.service;

import com.websystem.gift_card_bongiorno.model.Carta;
import com.websystem.gift_card_bongiorno.repository.ICartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class CartaService {

    @Autowired
    ICartaRepository Crepo;



    public Carta getById(Integer idCarta){

        Optional<Carta> opCarta = Crepo.findById(idCarta);


        if (opCarta.isEmpty()){
            return null;
        }
        else return opCarta.get();


    }

    public Carta setStatoById(Integer id){

        Carta carta=this.getById(id);

        if(carta.getStato().equals("attiva")){
            carta.setStato("non attiva");
        }
        else if(carta.getStato().equals("non attiva")){
            carta.setStato("attiva");
        }

        Crepo.save(carta);

        return carta;
    }

    public Carta newCarta(float credito){

        Carta carta=new Carta(credito,"attiva");
        return Crepo.save(carta);

    }

    public Iterable<Carta>  getCarte(Authentication authentication) {

        return Crepo.findAll();


    }



}
