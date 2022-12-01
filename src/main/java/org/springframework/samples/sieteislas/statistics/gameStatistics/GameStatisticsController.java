package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.game.Game;
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


    private String GAMESTATISTICS_LISTING = "/gameStatistics/previousGamesListing";
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
        Collection<List<String>> playerPointsMaps = playerPointsService.getAllPlayerPointsMaps(gameStats);
        model.put("gameStatistics", gameStats);
        model.put("playerPointsMaps", playerPointsMaps);
        return GAMESTATISTICS_LISTING;
    }

    @GetMapping("/dashboard")
    public String getDashboardData(Map<String, Object> model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        model.put("globalTimePlayed", gameStatisticsService.getTimePlayedGlobalMap());
        model.put("globalNumberGames", gameStatisticsService.getNumberGamesGlobalMap());
        model.put("globalPoints", playerPointsService.getPointsGlobal());
        if(!currentUser.equals("anonymousUser")) {
            model.put("userTimePlayed", playerPointsService.getTimePlayedUserMap(currentUser));
            model.put("userNumberGames", playerPointsService.getNumberGamesUserMap(currentUser));
            model.put("userPoints", playerPointsService.getPointsUser(currentUser));
        }
        return DASHBOARD;
    }

}
