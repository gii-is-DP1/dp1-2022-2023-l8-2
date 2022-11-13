package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer> {


}
