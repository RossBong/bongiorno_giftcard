package com.websystem.gift_card_bongiorno.repository;


import com.websystem.gift_card_bongiorno.model.Transazione;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransazioneRepository extends CrudRepository<Transazione,Integer> {



    @Query(value="select * from transazioni where cod_utente= ?1 order by transazioni.id_transazione DESC ", nativeQuery=true)
    public List<Transazione> loadUsingUtenteNativeQuery(Integer cod_utente);


    //query personalizzata per ottenere i dati in ordine decrescente di id per una migliore visualizzazione
    @Query(value="select * from transazioni  order by transazioni.id_transazione DESC ", nativeQuery=true)
    public List<Transazione> findAllOrderByIdDesc();


}
