package com.logmein.cardgame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logmein.cardgame.model.Card;
import com.logmein.cardgame.model.Deck;
import com.logmein.cardgame.model.FaceValue;
import com.logmein.cardgame.model.Suit;
import com.logmein.cardgame.model.repositories.DeckRepository;

@Service
public class DeckService {
	
	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private CardService cardService;
	
	public Deck createDeck() {
		Deck deck = new Deck();
		
		for(Suit suit : Suit.values()) {
			for(FaceValue faceValue : FaceValue.values() ) {
				Card card = new Card(suit, faceValue, faceValue.ordinal() + 1);
				deck.addCard(card);
			}
		}
		return deckRepository.save(deck);
	}
	
	public Deck getDeck(Long deckId) {
		Optional<Deck> deckOptional = deckRepository.findById(deckId);
		if(deckOptional.isPresent()) {
			return deckOptional.get();
		}
		return null;
	}
}
