package org.springframework.samples.sieteislas.statistics.playerStatistics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatisticsRepository extends CrudRepository<PlayerStatistics, Integer>{
	

}
