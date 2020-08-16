package com.logmein.cardgame.model.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.logmein.cardgame.model.Card;
import com.logmein.cardgame.model.Suit;

public interface CardRepository extends CrudRepository<Card, Long> {

	List<Card> findAllByPlayerId(Long playerId);
	
	Long countByDeckIdAndSuitAndPlayerIdIsNull(Long deckId, Suit suit);

	
}
