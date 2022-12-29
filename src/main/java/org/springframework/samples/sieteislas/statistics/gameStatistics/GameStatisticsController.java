package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class GameStatisticsController {


    private String GAMESTATISTICS_LISTING_GLOBAL = "/gameStatistics/previousGamesListingGlobal";
    private String GAMESTATISTICS_LISTING_USER = "/gameStatistics/previousGamesListingUser";
    private String DASHBOARD = "/gameStatistics/statisticsDashboard";
    private String PLAYERRANKING_LISTING = "/gameStatistics/playerRankingListing";
    private final GameStatisticsService gameStatisticsService;
    private final PlayerPointsService playerPointsService;


    @Autowired
    public GameStatisticsController(GameStatisticsService gameStatisticsService,
                                    PlayerPointsService playerPointsService) {
        this.gameStatisticsService = gameStatisticsService;
        this.playerPointsService = playerPointsService;
    }

    @GetMapping("/previousGames")
    public String getAllPreviousGamesGlobal(Map<String, Object> model) {
        Collection<GameStatistics> prevGames = gameStatisticsService.getAllGameStatistics();
        Collection<List<String>> playerPointsMaps = playerPointsService.getAllPlayerPointsMaps(prevGames);
        model.put("gameStatistics", prevGames);
        model.put("playerPointsMaps", playerPointsMaps);
        return GAMESTATISTICS_LISTING_GLOBAL;
    }

    @GetMapping("/previousGames/user")
    public String getAllPreviousGamesUser(Map<String, Object> model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!currentUser.equals("anonymousUser")) {
            Collection<List<String>> playerPointsMaps = playerPointsService.getAllPlayerPointsMaps(gameStatisticsService.getAllGameStatistics());
            playerPointsMaps.removeIf(e -> !e.contains(currentUser));
            model.put("playerPointsMaps", playerPointsMaps);
            model.put("previousGamesUser", playerPointsService.getPreviousGamesUser(currentUser));
        }
        return GAMESTATISTICS_LISTING_USER;
    }

    @GetMapping("/dashboard")
    public String getDashboardData(Map<String, Object> model) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.put("globalTimePlayed", gameStatisticsService.getTimePlayedGlobalMap());
        model.put("globalNumberGames", gameStatisticsService.getNumberGamesGlobalMap());
        model.put("globalPoints", playerPointsService.getPointsGlobal());
        if (!currentUser.equals("anonymousUser")) {
            model.put("userTimePlayed", playerPointsService.getTimePlayedUserMap(currentUser));
            model.put("userNumberGames", playerPointsService.getNumberGamesUserMap(currentUser));
            model.put("userPoints", playerPointsService.getPointsUser(currentUser));
        }
        return DASHBOARD;
    }

    @GetMapping("/playerRanking")
    public String getPlayerRanking(Map<String, Object> model) {
        model.put("playerRankingWins", playerPointsService.getPlayerRankingWins());
        model.put("playerRankingPoints", playerPointsService.getPlayerRankingPoints());
        return PLAYERRANKING_LISTING;
    }
}
