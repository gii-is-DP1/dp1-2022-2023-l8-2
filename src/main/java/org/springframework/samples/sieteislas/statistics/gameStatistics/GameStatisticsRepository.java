package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer> {

    @Query("SELECT round(sum(duration),2) FROM GameStatistics ")
    public Double findTotalTimePlayed();

    @Query("SELECT round(min(duration),2) FROM GameStatistics")
    public Double findMinTimePlayed();

    @Query("SELECT round(max(duration),2) FROM GameStatistics")
    public Double findMaxTimePlayed();

    @Query("SELECT round(avg(duration),2) FROM GameStatistics")
    public Double findAvgTimePlayed();

    @Query("SELECT count(g) FROM GameStatistics g")
    Integer getTotalNumberGames();

    @Query("SELECT count(g) FROM GameStatistics g GROUP BY g.month, g.year")
    Collection<Integer> getNumberGames();


}
