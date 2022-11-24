package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PlayerPointsRepository extends CrudRepository<PlayerPointsMap, Integer> {

    @Query("SELECT sum(points) FROM PlayerPointsMap")
    public Integer findTotalPoints();

    @Query("SELECT min(points) FROM PlayerPointsMap")
    public Integer findMinPoints();

    @Query("SELECT max(points) FROM PlayerPointsMap")
    public Integer findMaxPoints();

    @Query("SELECT avg(points) FROM PlayerPointsMap")
    public Integer findAvgPoints();


    @Query("SELECT sum(p.gameStatistics.duration) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findTotalTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT min(p.gameStatistics.duration) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findMinTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT max(p.gameStatistics.duration) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findMaxTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT avg(p.gameStatistics.duration) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findAvgTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT count(p) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer getNumberGamesByUser(@Param("currentUser") String currentUser);
}
