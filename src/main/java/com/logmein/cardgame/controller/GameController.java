package com.logmein.cardgame.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logmein.cardgame.model.Game;
import com.logmein.cardgame.model.Player;
import com.logmein.cardgame.service.GameService;

@RestController
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping("/games")
	public ArrayList<Game> getGames() {
		return gameService.getGames();
	}
	
	@PostMapping("/games")
	public Game createGame() {
		return gameService.createGame();
	}
	
	@GetMapping("/games/{gameId}")
	public Game getGame(@PathVariable Long gameId) {
		return gameService.getGame(gameId);
	}
	
	@DeleteMapping("/games/{gameId}")
	public void deleteGame(@PathVariable Long gameId) {
		gameService.deleteGame(gameId);
	}
	
	@PostMapping("/games/{gameId}/decks/{deckId}")
	public Game addDeck(@PathVariable Long gameId, @PathVariable Long deckId) {
		return gameService.addDeck(gameId, deckId);
	}
	
	@GetMapping("/games/{gameId}/players")
	public ArrayList<Player> addPlayer(@PathVariable Long gameId) {
		return gameService.getPlayers(gameId);
	}
	
	@PostMapping("/games/{gameId}/players/{playerId}")
	public Game addPlayer(@PathVariable Long gameId, @PathVariable Long playerId) {
		return gameService.addPlayer(gameId, playerId);
	}
	
	@DeleteMapping("/games/{gameId}/players/{playerId}")
	public Game deletePlayer(@PathVariable Long gameId, @PathVariable Long playerId) {
		return gameService.deletePlayer(gameId, playerId);
	}
	
	@PostMapping("/games/{gameId}/deal-cards")
	public Game dealCards(@PathVariable Long gameId) {
		return gameService.dealCards(gameId);
	}
	
	@GetMapping("/games/{gameId}/left-cards")
	public ArrayList<String> lefCards(@PathVariable Long gameId) {
		return gameService.leftCards(gameId);
	}
	
	@GetMapping("/games/{gameId}/shuffle")
	public void shuffle(@PathVariable Long gameId) {
		gameService.shuffle(gameId);
	}
	
	@RequestMapping("/")
	public String hello() {
		return "hello this is card game api";
	}
}
