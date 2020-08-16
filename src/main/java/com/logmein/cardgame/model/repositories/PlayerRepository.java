package com.logmein.cardgame.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.logmein.cardgame.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

	List<Player> findAllByGameIdOrderByTotalValueDesc(Long gameId);
	
}
