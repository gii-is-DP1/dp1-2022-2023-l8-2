package org.springframework.samples.sieteislas.statistics.achievement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatistics;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsRepository;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

    private AchievementRepository achievementRepo;
    private PlayerPointsRepository playerPointsRepo;
    private PlayerPointsService playerPointsService;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepo = achievementRepository;
    }

    public Collection<Achievement> getAllAchievements() {
        return (Collection<Achievement>) achievementRepo.findAll();
    }
    public Achievement getAchievementById(Integer id) {
        return achievementRepo.findById(id).get();
    }

    public void deleteAchievementById(Integer id){
        achievementRepo.deleteById(id);
    }

    public void saveAchievement(Achievement achievement){
        achievementRepo.save(achievement);
    }
    
    public void refreshAchievements(Game g) {
    	
    	GameStatistics gs = g.getStatistics();
    	
    	List<Achievement> allAchievements = new ArrayList<>();   	
    	achievementRepo.findAll().iterator()
    			.forEachRemaining(a->allAchievements.add(a));
    	
    	List<Player> players = gs.getGame().getPlayers();
    	
    	for(Player player:players) {
    		
    		List<Achievement> achievements = player.getAchievements();
    		
    		List<Achievement> achievementsToGet = achievements.stream()
    				.filter(a->!achievements.contains(a))
    				.toList();
    		
    		for(Achievement a:achievementsToGet) {
    			
    			String metricName = a.getMetric().getName();
    			Double d = .0;
    			
    			if(metricName.equals("gamesPlayed"))
    				d = playerPointsRepo.getTotalNumberGamesByUser(player.getUser().toString()) * 1.0;
    			else if(metricName.equals("gamesWon"))
    				d = playerPointsService.getWins(player) * 1.0;
    			else if(metricName.equals("points"))
    				d = playerPointsRepo.findTotalPointsUser(player.getUser().toString()) * 1.0;
    			else System.out.println("ERROR: Metric not found.");
    			
    			if(a.getThreshold() < d) {
    				
    				achievements.add(a);
    				player.setAchievements(achievements);
    			}
    		}
    	}
    }
    
}
