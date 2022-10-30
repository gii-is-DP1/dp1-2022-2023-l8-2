package org.springframework.samples.petclinic.statistics.playerStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatisticsService {
    private final PlayerStatisticsRepository playerStatisticsRepository;
    
    @Autowired
    public PlayerStatisticsService(PlayerStatisticsRepository playerStatisticsRepository){
        this.playerStatisticsRepository = playerStatisticsRepository;
    }
}
