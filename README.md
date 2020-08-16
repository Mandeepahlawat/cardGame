# README

### Features Completed with their API endpoints:

#### Create a game:

* API Endpint - http://localhost:8080/games
* HTTP Verb - POST
* Response:

```
{
    "id": 1,
    "players": null,
    "decks": null
}
```

#### Delete a game:

* API Endpint - http://localhost:8080/games/{gameId}
* HTTP Verb - DELETE

#### Create a deck:

* API Endpint - http://localhost:8080/decks
* HTTP Verb - POST
* Response:

```
{
    "id": 3,
    "cards": [
        {
            "id": 4,
            "suit": "HEARTS",
            "faceValue": "ACE",
            "value": 1
        },
        {
            "id": 5,
            "suit": "HEARTS",
            "faceValue": "TWO",
            "value": 2
        },
        {
            "id": 6,
            "suit": "HEARTS",
            "faceValue": "THREE",
            "value": 3
        },
        {
            "id": 7,
            "suit": "HEARTS",
            "faceValue": "FOUR",
            "value": 4
        },
        .
        .
        .
    ]
}
```

#### Add a deck to a game

* API Endpint - http://localhost:8080/games/{gameId}/decks/{deckId}
* HTTP Verb - POST
* Response:

```
{
    "id": 2,
    "players": [],
    "decks": [
        {
            "id": 3,
            "cards": [
                {
                    "id": 4,
                    "suit": "HEARTS",
                    "faceValue": "ACE",
                    "value": 1
                },
                {
                    "id": 5,
                    "suit": "HEARTS",
                    "faceValue": "TWO",
                    "value": 2
                },
                .
                .
                .
            ]
        }
    ]
}
```

#### Create a Player

* API Endpint - http://localhost:8080/players
* HTTP Verb - POST
* Request HEADER - "Content-Type" : "application/json"
* Request Body - {"name" : "Player 1"}
* Response:

```
{
    "id": 56,
    "cards": [],
    "name": "Player 1",
    "totalValue": 0
}
```

#### Add a Player to Game

* API Endpint - http://localhost:8080/games/{gameId}/players/{playerId}
* HTTP Verb - POST
* Response:

```
{
    "id": 2,
    "players": [
        {
            "id": 56,
            "cards": [],
            "name": "Player 1",
            "totalValue": 0
        }
    ],
    "decks": [
        {
            "id": 3,
            "cards": [
                {
                    "id": 4,
                    "suit": "HEARTS",
                    "faceValue": "ACE",
                    "value": 1
                },
                {
                    "id": 5,
                    "suit": "HEARTS",
                    "faceValue": "TWO",
                    "value": 2
                },
                .
                .
                .
            ]
        }
    ]
}
```

#### Delete a Player from Game

* API Endpint - http://localhost:8080/games/{gameId}/players/{playerId}
* HTTP Verb - DELETE
* Response:

```
{
    "id": 2,
    "players": [],
    "decks": [
        {
            "id": 3,
            "cards": [
                {
                    "id": 4,
                    "suit": "HEARTS",
                    "faceValue": "ACE",
                    "value": 1
                },
                {
                    "id": 5,
                    "suit": "HEARTS",
                    "faceValue": "TWO",
                    "value": 2
                },
                .
                .
                .
            ]
        }
    ]
}
```

#### Deal cards to a player in a game from the game deck

* This randomly selects player for the first call and then in successive calls uses the other remaining players. After end of all the players it again starts the process from first player
* This API assumes that game has been assigned decks and players
* API Endpint - http://localhost:8080/games/{gameId}/deal-cards
* HTTP Verb - POST
* Response:

```
{
    "id": 2,
    "players": [
        {
            "id": 56,
            "cards": [
                {
                    "id": 4,
                    "suit": "HEARTS",
                    "faceValue": "ACE",
                    "value": 1
                }
            ],
            "name": "Player 1",
            "totalValue": 1
        }
    ],
    "decks": [
        {
            "id": 3,
            "cards": [
                {
                    "id": 4,
                    "suit": "HEARTS",
                    "faceValue": "ACE",
                    "value": 1
                },
                .
                .
            ]
        }
    ]
}
```

#### Get the list of cards for a player

* API Endpint - http://localhost:8080/players/{playerId}/cards
* HTTP Verb - GET
* Response:

```
[
    {
        "id": 4,
        "suit": "HEARTS",
        "faceValue": "ACE",
        "value": 1
    }
]
```

#### Get the list of players in a game along with the total added value of all the cards each player holds. Then sort the list in descending order

* API Endpint - http://localhost:8080/games/{gameId}/players
* HTTP Verb - GET
* Response:

```
[
    {
        "id": 58,
        "cards": [
            {
                "id": 5,
                "suit": "HEARTS",
                "faceValue": "TWO",
                "value": 2
            }
        ],
        "name": "Player 2",
        "totalValue": 2
    },
    {
        "id": 56,
        "cards": [
            {
                "id": 4,
                "suit": "HEARTS",
                "faceValue": "ACE",
                "value": 1
            }
        ],
        "name": "Player 1",
        "totalValue": 1
    }
]
```

#### Get the count of how many cards per suit are left undealt in the deck

* API Endpint - http://localhost:8080/games/{gameId}/left-cards
* HTTP Verb - GET
* Response:

```
[
    "13 Spades",
    "13 Diamonds",
    "11 Hearts",
    "13 Clubs"
]
```

#### Shuffle the game deck

* This shuffles the cards in random order
* API Endpint - http://localhost:8080/games/{gameId}/shuffle
* HTTP Verb - GET


### Future Work:

* Handle exceptions in the API, right now if any GET API can't find a record then it just returns null. We can raise Exception that object with corresponding id doesn't exists.
* Add Validations to Player, so no 2 players can have same name.
* Add Junit Test cases for apis.
* Optimize Count API for remaining cards.