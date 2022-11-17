package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStatisticsService {

    private final GameStatisticsRepository gameStatisticsRepository;

    @Autowired
    public GameStatisticsService(GameStatisticsRepository gameStatisticsRepository){
        this.gameStatisticsRepository = gameStatisticsRepository;
    }

    public Collection<GameStatistics> getAllGameStatistics() {
        return (Collection<GameStatistics>) gameStatisticsRepository.findAll();
    }

    public Double getMaxTimePlayed() {
        return gameStatisticsRepository.findMaxTimePlayed();
    }

    public Double getAvgTimePlayed() {
        return gameStatisticsRepository.findAvgTimePlayed();
    }

    public Double getMinTimePlayed() {
        return gameStatisticsRepository.findMinTimePlayed();
    }

    public Double getTotalTimePlayed() {
        return gameStatisticsRepository.findTotalTimePlayed();
    }

    public Integer getNumberGames() {
        return gameStatisticsRepository.getNumberGames();
    }




}
