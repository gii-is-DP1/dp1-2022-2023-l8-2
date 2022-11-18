package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer> {

    @Query("SELECT sum(duration) FROM GameStatistics ")
    public Double findTotalTimePlayed();

    @Query("SELECT min(duration) FROM GameStatistics")
    public Double findMinTimePlayed();

    @Query("SELECT max(duration) FROM GameStatistics")
    public Double findMaxTimePlayed();

    @Query("SELECT avg(duration) FROM GameStatistics")
    public Double findAvgTimePlayed();

    @Query("SELECT count(g) FROM GameStatistics g")
    Integer getNumberGames();
}
