package com.logmein.cardgame.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logmein.cardgame.model.Player;
import com.logmein.cardgame.model.Card;
import com.logmein.cardgame.service.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping("/players")
	public Player createPlayer(@RequestBody Player player) {
		return playerService.createPlayer(player);
	}
	
	@GetMapping("/players/{playerId}/cards")
	public ArrayList<Card> getCards(@PathVariable Long playerId) {
		return playerService.getCards(playerId);
	}
}
