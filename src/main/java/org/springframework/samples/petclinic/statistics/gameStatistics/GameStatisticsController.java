package org.springframework.samples.petclinic.statistics.gameStatistics;

import org.springframework.stereotype.Controller;

@Controller
public class GameStatisticsController {
    
    private final GameStatisticsService gameStatisticsService;

    public GameStatisticsController(GameStatisticsService gameStatisticsService){
        this.gameStatisticsService = gameStatisticsService;
    }

}
