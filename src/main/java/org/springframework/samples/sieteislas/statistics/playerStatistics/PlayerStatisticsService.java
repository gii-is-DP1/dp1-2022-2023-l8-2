package org.springframework.samples.sieteislas.statistics.playerStatistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsMap;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsService {
    
    private final PlayerStatisticsRepository playerStatisticsRepository;
    
    @Autowired
    public PlayerStatisticsService(PlayerStatisticsRepository playerStatisticsRepository){
        this.playerStatisticsRepository = playerStatisticsRepository;
    }
    
    public void refreshStatistics(Game g) {
    	
    	List<Player> players = g.getPlayers();
    	
    	List<PlayerPointsMap> points = g.getStatistics().getPlayerPoints();
    	Double duration = g.getStatistics().getDuration();
    	
    	for(Player p:players) {
    		
    		PlayerStatistics stats = p.getStatistics();
    		Integer playerPoints = points.stream()
    				.filter(x -> x.getPlayer().getId().equals(p.getId()))
    				.mapToInt(PlayerPointsMap::getPoints)
    				.sum();
    		
    		stats.setGamesPlayed(stats.getGamesPlayed() + 1);
    		stats.setTimePlayed(stats.getTimePlayed() + duration);
    		stats.setTotalPoints(stats.getTotalPoints() + playerPoints);
    		//stats.setGamesWon missing
    	}
    	
    }
}
