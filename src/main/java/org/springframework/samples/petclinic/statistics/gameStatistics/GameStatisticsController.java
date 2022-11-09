package org.springframework.samples.petclinic.statistics.gameStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GameStatisticsController {
    
    private final GameStatisticsService gameStatisticsService;

    @Autowired
    public GameStatisticsController(GameStatisticsService gameStatisticsService){
        this.gameStatisticsService = gameStatisticsService;
    }

}
