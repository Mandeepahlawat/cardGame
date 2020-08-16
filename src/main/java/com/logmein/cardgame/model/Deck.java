package com.logmein.cardgame.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Deck {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="deck")
	private List<Card> cards;
	
	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	
	public Deck() {
		this.cards = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Card> getCards() {
		return cards;
	}

	@JsonIgnore
	public void addCard(Card card) {
		this.cards.add(card);
		card.setDeck(this);
	}
}
