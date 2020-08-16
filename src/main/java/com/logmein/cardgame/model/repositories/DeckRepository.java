package com.logmein.cardgame.model.repositories;

import org.springframework.data.repository.CrudRepository;
import com.logmein.cardgame.model.Deck;

public interface DeckRepository extends CrudRepository<Deck, Long> {

}
