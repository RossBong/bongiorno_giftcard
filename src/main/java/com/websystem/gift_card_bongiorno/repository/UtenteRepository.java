package com.websystem.gift_card_bongiorno.repository;



import com.websystem.gift_card_bongiorno.model.Utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    Utente findByEmail(String email);

}