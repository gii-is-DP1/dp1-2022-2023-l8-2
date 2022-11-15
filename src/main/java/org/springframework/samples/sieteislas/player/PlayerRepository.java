package org.springframework.samples.sieteislas.player;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String>{

    @Query("SELECT p FROM Player p WHERE p.user LIKE :user")
    Player findPlayerByUser(@Param("user") User user);
}
