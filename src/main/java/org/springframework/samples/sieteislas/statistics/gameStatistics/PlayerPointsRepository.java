package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PlayerPointsRepository extends CrudRepository<PlayerPointsMap, Integer> {


    @Query("SELECT p FROM PlayerPointsMap p WHERE p.gameStatistics.id =:gameStatisticsId")
    Collection<PlayerPointsMap> findByGameStatistics(@Param("gameStatisticsId") Integer gameStatisticsId);
}
