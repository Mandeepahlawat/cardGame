package com.logmein.cardgame.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logmein.cardgame.model.Card;
import com.logmein.cardgame.model.Suit;
import com.logmein.cardgame.model.repositories.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
	public Card createCard(Card card) {
		return cardRepository.save(card);
	}
	
	public ArrayList<Card> getAllCardsByPlayer(Long playerId) {
		ArrayList<Card> cards = new ArrayList<>();
		cardRepository.findAllByPlayerId(playerId).forEach(cards :: add);
		return cards;
		
	}

//	public ArrayList<String> getRemainigCards(Long deckId) {
//		ArrayList<String> remaining = new ArrayList<>();
//		Long remainingSpades = cardRepository.countByDeckIdAndSuitAndPlayerIdIsNull(2L, Suit.SPADES);
//		remaining.add(remainingSpades + " Spades");
//		return remaining;
//		
//	}
	
	public Long getRemainingCardsByDeckAndSuit(Long deckId, Suit suit) {
		return cardRepository.countByDeckIdAndSuitAndPlayerIdIsNull(deckId, suit);
	}
}
