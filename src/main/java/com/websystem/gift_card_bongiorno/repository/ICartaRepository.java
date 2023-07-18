package com.websystem.gift_card_bongiorno.repository;

import com.websystem.gift_card_bongiorno.model.Carta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartaRepository extends CrudRepository<Carta,Integer> {

}
