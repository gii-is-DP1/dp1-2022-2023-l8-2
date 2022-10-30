package org.springframework.samples.petclinic.statistics.playerStatistics;

import org.springframework.stereotype.Controller;

@Controller
public class PlayerStatisticsController {
	   
    private final PlayerStatisticsService playerStatisticsService;

    public PlayerStatisticsController(PlayerStatisticsService playerStatisticsService){
        this.playerStatisticsService = playerStatisticsService;
    }

}
