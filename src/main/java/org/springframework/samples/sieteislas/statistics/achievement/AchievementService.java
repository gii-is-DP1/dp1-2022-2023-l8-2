package org.springframework.samples.sieteislas.statistics.achievement;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

    private AchievementRepository achievementRepository;


    @Autowired
    public AchievementService(AchievementRepository achievementRepository) {
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

}
