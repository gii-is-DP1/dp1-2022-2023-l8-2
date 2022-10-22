package org.springframework.samples.petclinic.statistics.gameStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticsService {

    private final GameStatisticsRepository gameStatisticsRepository;
    
    @Autowired
    public GameStatisticsService(GameStatisticsRepository gameStatisticsRepository){
        this.gameStatisticsRepository = gameStatisticsRepository;
    }
}
