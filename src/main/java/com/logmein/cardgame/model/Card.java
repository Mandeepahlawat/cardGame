package com.logmein.cardgame.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Card {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Enumerated(EnumType.STRING)
	private Suit suit;
	
	@Enumerated(EnumType.STRING)
	private FaceValue faceValue;
	
	@ManyToOne
	@JoinColumn(name="deck_id")
	private Deck deck;
	
	@ManyToOne
	@JoinColumn(name="player_id")
	private Player player;
	
	private int value;
	
	public Card() {}

	public Card(Suit suit, FaceValue faceValue, int value) {
		super();
		this.suit = suit;
		this.faceValue = faceValue;
		this.value = value;
	};
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	@JsonIgnore
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public FaceValue getFaceValue() {
		return this.faceValue;
	}

	public void setFaceValue(FaceValue value) {
		this.faceValue = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@JsonIgnore
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
