package org.springframework.samples.sieteislas.statistics.achievement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.statistics.playerStatistics.PlayerStatistics;
import org.springframework.samples.sieteislas.statistics.playerStatistics.PlayerStatisticsRepository;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

    private AchievementRepository achievementRepository;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository,
    		PlayerStatisticsRepository playerStatsRepository) {
        this.achievementRepository = achievementRepository;
    }

    public Collection<Achievement> getAllAchievements() {
        return (Collection<Achievement>) achievementRepository.findAll();
    }
    public Achievement getAchievementById(Integer id) {
        return achievementRepository.findById(id).get();
    }

    public void deleteAchievementById(Integer id){
        achievementRepository.deleteById(id);
    }

    public void saveAchievement(Achievement achievement){
        achievementRepository.save(achievement);
    }
    
    public void refreshAchievements(List<Player> players) {
    	
    	List<PlayerStatistics> stats = players.stream()
    			.map(Player::getStatistics)
    			.toList();
    	
    	List<Achievement> allAchievements = new ArrayList<>();   	
    	achievementRepository.findAll().iterator()
    			.forEachRemaining(a->allAchievements.add(a));
    	
    	for(PlayerStatistics s:stats) {
    		
    		Player player = s.getPlayer();
    		List<Achievement> achievements = player.getAchievements();
    		
    		List<Achievement> achievementsToGet = achievements.stream()
    				.filter(a->!achievements.contains(a))
    				.toList();
    		
    		for(Achievement a:achievementsToGet) {
    			
    			String metricName = a.getMetric().getName();
    			Double d = .0;
    			
    			if(metricName.equals("gamesPlayed"))
    				d = s.getGamesPlayed() * 1.0;
    			else if(metricName.equals("gamesWon"))
    				d = s.getGamesWon() * 1.0;
    			else if(metricName.equals("points"))
    				d = s.getTotalPoints() * 1.0;
    			else System.out.println("ERROR: Metric not found.");
    			
    			if(a.getThreshold() < d) {
    				
    				achievements.add(a);
    				player.setAchievements(achievements);
    			}
    		}
    	}
    }
    
}
