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
public class Player {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="player")
	private List<Card> cards;
	
	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	private String name;
	
	private Integer totalValue = 0;
	
	public Player() {
		this.cards = new ArrayList<>();
	}
	
	public Player(ArrayList<Card> cards, String name) {
		super();
		this.cards = cards;
		this.name = name;
	}
	
	@JsonIgnore
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCard(Card card) {
		this.cards.add(card);
		this.setTotalValue(this.totalValue + card.getValue());
		card.setPlayer(this);
	}

	public Integer getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Integer totalValue) {
		this.totalValue = totalValue;
	}	
	
}
