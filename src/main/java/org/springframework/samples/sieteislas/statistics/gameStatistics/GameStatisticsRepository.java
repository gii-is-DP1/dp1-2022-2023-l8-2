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
    public Double totalTimePlayed();

    @Query("SELECT min(duration) FROM GameStatistics")
    public Double minTimePlayed();

    @Query("SELECT max(duration) FROM GameStatistics")
    public Double maxTimePlayed();

    @Query("SELECT avg(duration) FROM GameStatistics")
    public Double avgTimePlayed();

}
