package org.springframework.samples.petclinic.statistics.gameStatistics;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatisticsRepository extends CrudRepository<GameStatistics, Integer>{
    
}
