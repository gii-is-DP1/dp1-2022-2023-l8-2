package org.springframework.samples.sieteislas.game;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{

	@Query("SELECT g FROM Game g WHERE g.active LIKE :active")
	List<Game> getActiveGames(@Param("active") Boolean active);

    @Query("SELECT count(g) FROM Game g")
    Integer getNumberGames();

    @Modifying
    @Query("UPDATE Game g SET g.creatorUsername = :username WHERE g.id = :id")
    void updateCreator(@Param("id") Integer id, @Param("username") String username);

    @Modifying
    @Query("UPDATE Game g SET g.active = :b WHERE g.id = :id")
    void toggleActive(@Param("id") Integer id, @Param("b") boolean b);
    
    @Modifying
    @Query("UPDATE Game g SET g.diceRoll = -1 WHERE g.id = :id")
	void setDiceNull(@Param("id") Integer id);
}
