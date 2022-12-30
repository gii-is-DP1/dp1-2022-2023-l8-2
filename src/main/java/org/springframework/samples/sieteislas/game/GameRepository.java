package org.springframework.samples.sieteislas.game;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{

	@Query("SELECT g FROM Game g WHERE g.active LIKE :active")
	Page<Game> getActiveGames(@Param("active") Boolean active, Pageable pageable);

    @Query("SELECT count(g) FROM Game g")
    Integer getNumberGames();

    @Modifying
    @Query("UPDATE Game g SET g.creatorUsername = :username WHERE g.id = :id")
    void updateCreator(@Param("id") Integer id, @Param("username") String username);

    @Modifying
    @Query("UPDATE Game g SET g.active = :b WHERE g.id = :id")
    void toggleActive(@Param("id") Integer id, @Param("b") boolean b);

    @Modifying
    @Query("UPDATE Game g SET g.hasRolledDice = true WHERE g.id = :id")
	void toggleHasRolledDiceTrue(@Param("id") Integer id);

    @Modifying
    @Query("UPDATE Game g SET g.hasRolledDice = false WHERE g.id = :id")
    void toggleHasRolledDiceFalse(Integer id);

    @Modifying
    @Query("UPDATE Game g SET g.numCardsToPay = :num WHERE g.id = :id")
    void setNumCardsToPay(@Param("id") Integer id, @Param("num") int num);

    @Modifying
    @Query("UPDATE Game g SET g.playerTurn = :nextPlayer WHERE g.id = :id")
    void setPlayerTurn(@Param("id") Integer id, @Param("nextPlayer") int nextPlayer);


}
