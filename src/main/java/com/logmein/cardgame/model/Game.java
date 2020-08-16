package com.logmein.cardgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Game {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="game")
	private List<Player> players;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="game")
	private List<Deck> decks;
	

	private static Integer previousDealtPlayerPosition = null;
	
	private static Integer previousDealtCardPosition = null;
	
	private static ArrayList<Card> gameCards = null;
	
	public Game() {}

	public Game(ArrayList<Player> players) {
		super();
		this.players = players;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}
	
	public void addDeck(Deck deck) {
		this.decks.add(deck);
		deck.setGame(this);
	}

	public void addPlayer(Player player) {
		this.players.add(player);
		player.setGame(this);
	}
	
	public void deletePlayer(Player player) {
		this.players.remove(player);
		player.setGame(null);
	}
	
	@JsonIgnore
	public Card getCard() {
		
		if(Game.gameCards == null) {
			this.getGameCards();
		}
		
		if(Game.previousDealtCardPosition == null) {
			Game.previousDealtCardPosition = 0;
		}
		else if (Game.previousDealtCardPosition + 1 == Game.gameCards.size()) {
			return null;
		}
		else {
			Game.previousDealtCardPosition += 1;
		}
		
		Card card = Game.gameCards.get(Game.previousDealtCardPosition);
		
		if(card.getPlayer() != null) {
			card = this.getCard();
		}
		return card;
	}
	
	@JsonIgnore
	public Player getPlayer() {
		if(Game.previousDealtPlayerPosition == null || Game.previousDealtPlayerPosition + 1 == this.players.size()) {
			Game.previousDealtPlayerPosition = 0;
		}
		else {
			Game.previousDealtPlayerPosition += 1;
		}
		
		
		return this.players.get(Game.previousDealtPlayerPosition);
	}
	
	@JsonIgnore
	public ArrayList<Card> getGameCards(){
		if(Game.gameCards != null) {
			return Game.gameCards;
		}
		
		if(this.decks.size() == 0) {
			return null;
		}

		Game.gameCards = new ArrayList<>();
		for(Deck deck : this.decks) {
			for(Card card : deck.getCards()) {
				Game.gameCards.add(card);
			}
		}
		return Game.gameCards;
	}
	

	public void shuffle() {
		Collections.shuffle(Game.gameCards);
	}

}
