package com.logmein.cardgame.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logmein.cardgame.model.Card;
import com.logmein.cardgame.model.Deck;
import com.logmein.cardgame.model.Game;
import com.logmein.cardgame.model.Player;
import com.logmein.cardgame.model.Suit;
import com.logmein.cardgame.model.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private DeckService deckService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private CardService cardService;
	
	public ArrayList<Game> getGames() {
		ArrayList<Game> games = new ArrayList<>();
		
		gameRepository.findAll().forEach(games :: add);
		return games;
	}
	
	public Game getGame(Long gameId) {
		Optional<Game> gameOptional = gameRepository.findById(gameId);
		if(gameOptional.isPresent()) {
			return gameOptional.get();
		}
		return null;
	}

	public Game createGame() {
		Game game = new Game();
		return gameRepository.save(game);
	}

	public void deleteGame(Long gameId) {
		gameRepository.deleteById(gameId);
	}
	
	public Game addDeck(Long gameId, Long deckId) {
		Game game = this.getGame(gameId);
		if(game != null) {
			Deck deck = deckService.getDeck(deckId);
			if(deck != null) {
				game.addDeck(deck);
			}
			return gameRepository.save(game);
		}
		return null;
	}

	public Game addPlayer(Long gameId, Long playerId) {
		Game game = this.getGame(gameId);
		if(game != null) {
			Player player = playerService.getPlayer(playerId);
			if(player != null) {
				game.addPlayer(player);
			}
			return gameRepository.save(game);
		}
		return null;
	}
	
	public Game deletePlayer(Long gameId, Long playerId) {
		Game game = this.getGame(gameId);
		if(game != null) {
			Player player = playerService.getPlayer(playerId);
			if(player != null) {
				game.deletePlayer(player);
			}
			return gameRepository.save(game);
		}
		return null;
	}

	public Game dealCards(Long gameId) {
		Game game = this.getGame(gameId);
		if(game != null) {
			Card card = game.getCard();
			if(card != null) {
				Player player = game.getPlayer();
				player.addCard(card);
				return gameRepository.save(game);
			}
			return game;
		}
		return null;
	}
	
	public ArrayList<Player> getPlayers(Long gameId) {
		Game game = this.getGame(gameId);
		if(game != null) {
			return playerService.getAllPlayersByGameId(gameId);
		}
		return null;
	}

	public ArrayList<String> leftCards(Long gameId) {
		Game game = this.getGame(gameId);
		if(game != null) {
			ArrayList<String> result = new ArrayList<>();
			Long remainingSpades = 0L;
			Long remainingDiamonds = 0L;
			Long remainingHearts = 0L;
			Long remainingClubs = 0L;
			
			// TODO: This is not an efficient way of doing it, i can probably use groupBy here to get count
			// but in order to complete in time i'm leaving it for now
			for(Deck deck : game.getDecks()) {
				remainingSpades += cardService.getRemainingCardsByDeckAndSuit(deck.getId(), Suit.SPADES);
				remainingDiamonds += cardService.getRemainingCardsByDeckAndSuit(deck.getId(), Suit.DIAMONDS);
				remainingHearts += cardService.getRemainingCardsByDeckAndSuit(deck.getId(), Suit.HEARTS);
				remainingClubs += cardService.getRemainingCardsByDeckAndSuit(deck.getId(), Suit.CLUBS);
			}
			result.add(remainingSpades + " Spades");
			result.add(remainingDiamonds + " Diamonds");
			result.add(remainingHearts + " Hearts");
			result.add(remainingClubs + " Clubs");
			
			return result;
			
		}
		return null;
	}

	public void shuffle(Long gameId) {
		Game game = this.getGame(gameId);
		if(game != null) {
			game.shuffle();
		}
	}
	
	
}
