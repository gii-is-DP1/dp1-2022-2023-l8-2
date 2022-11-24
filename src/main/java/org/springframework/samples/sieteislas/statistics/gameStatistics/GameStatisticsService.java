package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Collections.max;
import static java.util.Collections.min;

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

    public Integer getTotalNumberGames() {
        return gameStatisticsRepository.getTotalNumberGames();
    }
    public Integer getMaxNumberGames() {
        return max(gameStatisticsRepository.getNumberGames());
    }
    public Integer getMinNumberGames() {
        return min(gameStatisticsRepository.getNumberGames());
    }
    public Integer getAvgNumberGames() {
        return (int)gameStatisticsRepository.getNumberGames().stream().mapToDouble(d -> d).average().orElse(0.0);
    }
}
