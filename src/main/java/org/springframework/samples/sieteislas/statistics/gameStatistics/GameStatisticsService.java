package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticsService {

    private final GameStatisticsRepository gameStatisticsRepository;

    @Autowired
    public GameStatisticsService(GameStatisticsRepository gameStatisticsRepository){
        this.gameStatisticsRepository = gameStatisticsRepository;
    }

     public Collection<GameStatistics> getAllGameStatistcs() {
        return (Collection<GameStatistics>) gameStatisticsRepository.findAll();
    }

}
