package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.stereotype.Controller;

@Controller
public class GameStatisticsController {

    private final GameStatisticsService gameStatisticsService;

    public GameStatisticsController(GameStatisticsService gameStatisticsService){
        this.gameStatisticsService = gameStatisticsService;
    }

}
