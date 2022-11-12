package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics/gameStatistics")
public class GameStatisticsController {

    
    private String GAMESTATISTICS_LISTING = "/gameStatistics/GameStatisticsListing";
    private final GameStatisticsService gameStatisticsService;

    @Autowired
    public GameStatisticsController(GameStatisticsService gameStatisticsService){
        this.gameStatisticsService = gameStatisticsService;
    }

    //GET ALL
    @GetMapping("/")
    public String getAllGameStatistcs(Map<String, Object> model) {
        Collection<GameStatistics> gameStats = gameStatisticsService.getAllGameStatistcs();
        model.put("gameStatistics", gameStats);
        return GAMESTATISTICS_LISTING;
    }

}
