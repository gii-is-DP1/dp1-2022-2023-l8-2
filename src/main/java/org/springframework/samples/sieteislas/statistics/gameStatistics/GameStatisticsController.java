package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public GameStatisticsController(GameStatisticsService gameStatisticsService,
                                    PlayerPointsService playerPointsService){
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Double avgTimePlayedUser = playerPointsService.getAvgTimePlayedByUser(currentUser);
        Double maxTimePlayedUser = playerPointsService.getMaxTimePlayedByUser(currentUser);
        Double minTimePlayedUser = playerPointsService.getMinTimePlayedByUser(currentUser);
        Double totalTimePlayedUser = playerPointsService.getTotalTimePlayedByUser(currentUser);
        Map<String, Double> timePlayedUserMap = new HashMap<String, Double>();
        timePlayedUserMap.put("avgTimePlayed", avgTimePlayedUser);
        timePlayedUserMap.put("maxTimePlayed", maxTimePlayedUser);
        timePlayedUserMap.put("minTimePlayed", minTimePlayedUser);
        timePlayedUserMap.put("totalTimePlayed", totalTimePlayedUser);
        model.put("globalTimePlayedUser", timePlayedUserMap);

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

        Integer totalNumberGames = gameStatisticsService.getNumberGames();
        Map<String, Integer> globalNumberGamesMap = new HashMap<String, Integer>();
        globalNumberGamesMap.put("totalNumberGames", totalNumberGames);
        model.put("globalNumberGames", globalNumberGamesMap);

        Integer totalNumberGamesUser = playerPointsService.getNumberGamesByUser(currentUser);
        Map<String, Integer> userNumberGamesMap = new HashMap<String, Integer>();
        userNumberGamesMap.put("totalNumberGames", totalNumberGamesUser);
        model.put("userNumberGames", userNumberGamesMap);

        Integer totalPoints = playerPointsService.getTotalPoints();
        Integer minPoints = playerPointsService.getMinPoints();
        Integer maxPoints = playerPointsService.getMaxPoints();
        Integer avgPoints = playerPointsService.getAvgPoints();
        Map<String, Integer> pointsMap = new HashMap<String, Integer>();
        pointsMap.put("totalPoints", totalPoints);
        pointsMap.put("minPoints", minPoints);
        pointsMap.put("maxPoints", maxPoints);
        pointsMap.put("avgPoints", avgPoints);
        model.put("globalPoints", pointsMap);
        return DASHBOARD;
    }

}
