package com.logmein.cardgame.model.repositories;

import org.springframework.data.repository.CrudRepository;
import com.logmein.cardgame.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

}
