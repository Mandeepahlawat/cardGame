package com.logmein.cardgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logmein.cardgame.model.Deck;
import com.logmein.cardgame.service.DeckService;

@RestController
public class DeckController {
	
	@Autowired
	private DeckService deckService;
	
	@PostMapping("/decks")
	public Deck createDeck() {
		return deckService.createDeck();
	}
}
