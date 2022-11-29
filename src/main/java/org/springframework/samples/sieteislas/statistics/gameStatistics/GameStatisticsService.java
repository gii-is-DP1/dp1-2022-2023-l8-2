package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Double> getTimePlayedGlobalMap(){
        Double avgTimePlayed = getAvgTimePlayed();
        Double maxTimePlayed = getMaxTimePlayed();
        Double minTimePlayed = getMinTimePlayed();
        Double totalTimePlayed = getTotalTimePlayed();
        Map<String, Double> timePlayedMap = new HashMap<String, Double>();
        timePlayedMap.put("avgTimePlayed", avgTimePlayed);
        timePlayedMap.put("maxTimePlayed", maxTimePlayed);
        timePlayedMap.put("minTimePlayed", minTimePlayed);
        timePlayedMap.put("totalTimePlayed", totalTimePlayed);
        return timePlayedMap;
    }

    public Map<String, Integer> getNumberGamesGlobalMap(){
        Integer totalNumberGames = getTotalNumberGames();
        Integer minNumberGames = getMinNumberGames();
        Integer maxNumberGames = getMaxNumberGames();
        Integer avgNumberGames = getAvgNumberGames();
        Map<String, Integer> globalNumberGamesMap = new HashMap<String, Integer>();
        globalNumberGamesMap.put("totalNumberGames", totalNumberGames);
        globalNumberGamesMap.put("minNumberGames", minNumberGames);
        globalNumberGamesMap.put("maxNumberGames", maxNumberGames);
        globalNumberGamesMap.put("avgNumberGames", avgNumberGames);
        return globalNumberGamesMap;
    }


}
