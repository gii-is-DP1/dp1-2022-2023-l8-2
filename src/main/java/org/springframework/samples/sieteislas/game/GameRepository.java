package org.springframework.samples.sieteislas.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{
	
	@Query("SELECT g FROM Game g WHERE g.active LIKE :active")
	List<Game> getActiveGames(@Param("active") Boolean active);

}
