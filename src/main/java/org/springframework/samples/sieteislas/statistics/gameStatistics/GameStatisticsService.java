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

    public Map<String, Double> getTimePlayedGlobalMap(){
        Double avgTimePlayed = gameStatisticsRepository.findAvgTimePlayed();
        Double maxTimePlayed = gameStatisticsRepository.findMaxTimePlayed();
        Double minTimePlayed = gameStatisticsRepository.findMinTimePlayed();
        Double totalTimePlayed = gameStatisticsRepository.findTotalTimePlayed();
        Map<String, Double> timePlayedMap = new HashMap<String, Double>();
        timePlayedMap.put("avgTimePlayed", avgTimePlayed);
        timePlayedMap.put("maxTimePlayed", maxTimePlayed);
        timePlayedMap.put("minTimePlayed", minTimePlayed);
        timePlayedMap.put("totalTimePlayed", totalTimePlayed);
        return timePlayedMap;
    }

    public Map<String, Integer> getNumberGamesGlobalMap(){
        Integer totalNumberGames = gameStatisticsRepository.getTotalNumberGames();
        Integer minNumberGames = min(gameStatisticsRepository.getNumberGames());
        Integer maxNumberGames = max(gameStatisticsRepository.getNumberGames());
        Integer avgNumberGames = (int)gameStatisticsRepository.getNumberGames().stream()
        		.mapToDouble(d -> d).average()
        		.orElse(0.0);
        Map<String, Integer> globalNumberGamesMap = new HashMap<String, Integer>();
        globalNumberGamesMap.put("totalNumberGames", totalNumberGames);
        globalNumberGamesMap.put("minNumberGames", minNumberGames);
        globalNumberGamesMap.put("maxNumberGames", maxNumberGames);
        globalNumberGamesMap.put("avgNumberGames", avgNumberGames);
        return globalNumberGamesMap;
    }
    
    public void save(GameStatistics stats) {
    	gameStatisticsRepository.save(stats);
    }
    
}
