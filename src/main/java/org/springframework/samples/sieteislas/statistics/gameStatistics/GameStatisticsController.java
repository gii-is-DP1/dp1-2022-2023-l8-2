package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class GameStatisticsController {


    private String GAMESTATISTICS_LISTING = "/gameStatistics/GameStatisticsListing";
    private String DASHBOARD = "/gameStatistics/statisticsDashboard";
    private final GameStatisticsService gameStatisticsService;
    private final PlayerPointsService playerPointsService;

    @Autowired
    public GameStatisticsController(GameStatisticsService gameStatisticsService, PlayerPointsService playerPointsService){
        this.gameStatisticsService = gameStatisticsService;
        this.playerPointsService = playerPointsService;
    }

    @GetMapping("/previousGames")
    public String getAllGameStatistics(Map<String, Object> model) {
        Collection<GameStatistics> gameStats = gameStatisticsService.getAllGameStatistics();
        Collection<String> playerPointsMaps = playerPointsService.getAllPlayerPointsMaps(gameStats);
        model.put("gameStatistics", gameStats);
        model.put("playerPointsMaps", playerPointsMaps);
        return GAMESTATISTICS_LISTING;
    }

    @GetMapping("/dashboard")
    public String getGlobalTimePlayed(Map<String, Object> model) {
        Double avgTimePlayed = gameStatisticsService.getAvgTimePlayed();
        Double maxTimePlayed = gameStatisticsService.getMaxTimePlayed();
        Double minTimePlayed = gameStatisticsService.getMinTimePlayed();
        Double totalTimePlayed = gameStatisticsService.getTotalTimePlayed();
        Map<String, Double> timePlayedMap = new HashMap<String, Double>();
        timePlayedMap.put("avgTimePlayed", avgTimePlayed);
        timePlayedMap.put("maxTimePlayed", maxTimePlayed);
        timePlayedMap.put("minTimePlayed", minTimePlayed);
        timePlayedMap.put("totalTimePlayed", totalTimePlayed);
        model.put("globalTimePlayed", timePlayedMap);
        return DASHBOARD;
    }



}
