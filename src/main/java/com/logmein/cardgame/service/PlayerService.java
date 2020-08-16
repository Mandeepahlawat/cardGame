package com.logmein.cardgame.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logmein.cardgame.model.Card;
import com.logmein.cardgame.model.Player;
import com.logmein.cardgame.model.repositories.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private CardService cardService;
	
	
	public Player getPlayer(Long playerId) {
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if(playerOptional.isPresent()) {
			return playerOptional.get();
		}
		return null;
	}
	
	public Player createPlayer(Player player) {
		return playerRepository.save(player);
	}

	public ArrayList<Card> getCards(Long playerId) {
		Player player = this.getPlayer(playerId);
		if(player != null) {
			return cardService.getAllCardsByPlayer(playerId);
		}
		return null;
	}
	
	public ArrayList<Player> getAllPlayersByGameId(Long gameId) {
		ArrayList<Player> players = new ArrayList<>();
		playerRepository.findAllByGameIdOrderByTotalValueDesc(gameId).forEach(players :: add);
		return players;
		
	}
	
}
