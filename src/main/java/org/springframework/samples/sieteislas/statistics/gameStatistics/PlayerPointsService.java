package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.*;

import static java.util.Collections.max;
import static java.util.Collections.min;

@Service
public class PlayerPointsService {

    private final PlayerPointsRepository playerPointsRepository;


    @Autowired
    public PlayerPointsService(PlayerPointsRepository playerPointsRepository) {
        this.playerPointsRepository = playerPointsRepository;
    }


    public Collection<String> getAllPlayerPointsMaps(Collection<GameStatistics> gameStatistics) {
        List<String> players = new ArrayList<>();
        List<String> playerNested = new ArrayList<>();
        for (GameStatistics gm: gameStatistics) {
            for (Player pl: gm.getGame().getPlayers()){
                playerNested.add(pl.getUser().getUsername());
            }
            players.add(playerNested.toString());
            playerNested.clear();
        }
        return players;
    }

    public Integer getMaxPoints() {
        return playerPointsRepository.findMaxPoints();
    }
    public Integer getMinPoints() {
        return playerPointsRepository.findMinPoints();
    }
    public Integer getAvgPoints() {
        return playerPointsRepository.findAvgPoints();
    }
    public Integer getTotalPoints() {
        return playerPointsRepository.findTotalPoints();
    }

    public Double getAvgTimePlayedByUser(String currentUser) {
        return playerPointsRepository.findAvgTimePlayedByUser(currentUser);
    }

    public Double getMaxTimePlayedByUser(String currentUser) {
        return playerPointsRepository.findMaxTimePlayedByUser(currentUser);
    }

    public Double getMinTimePlayedByUser(String currentUser) {
        return playerPointsRepository.findMinTimePlayedByUser(currentUser);
    }

    public Double getTotalTimePlayedByUser(String currentUser) {
        return playerPointsRepository.findTotalTimePlayedByUser(currentUser);
    }

    public Integer getTotalNumberGamesByUser(String currentUser) {
        return playerPointsRepository.getTotalNumberGamesByUser(currentUser);
    }
    public Integer getMaxNumberGamesByUser(String currentUser) {
        return max(playerPointsRepository.getGroupedNumberGamesByUser(currentUser));
    }
    public Integer getMinNumberGamesByUser(String currentUser) {
        return min(playerPointsRepository.getGroupedNumberGamesByUser(currentUser));
    }
    public Integer getAvgNumberGamesByUser(String currentUser) {
        return (int)playerPointsRepository.getGroupedNumberGamesByUser(currentUser).stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    public Map<String, Double> getTimePlayedUserMap(String currentUser){
        Double avgTimePlayedUser = getAvgTimePlayedByUser(currentUser);
        Double maxTimePlayedUser = getMaxTimePlayedByUser(currentUser);
        Double minTimePlayedUser = getMinTimePlayedByUser(currentUser);
        Double totalTimePlayedUser = getTotalTimePlayedByUser(currentUser);
        Map<String, Double> timePlayedUserMap = new HashMap<String, Double>();
        timePlayedUserMap.put("avgTimePlayed", avgTimePlayedUser);
        timePlayedUserMap.put("maxTimePlayed", maxTimePlayedUser);
        timePlayedUserMap.put("minTimePlayed", minTimePlayedUser);
        timePlayedUserMap.put("totalTimePlayed", totalTimePlayedUser);
        return timePlayedUserMap;
    }

    public Map<String, Integer> getNumberGamesUserMap(String currentUser){
        Integer totalNumberGamesUser = getTotalNumberGamesByUser(currentUser);
        Integer maxNumberGamesUser = getMaxNumberGamesByUser(currentUser);
        Integer minNumberGamesUser = getMinNumberGamesByUser(currentUser);
        Integer avgNumberGamesUser = getAvgNumberGamesByUser(currentUser);
        Map<String, Integer> userNumberGamesMap = new HashMap<String, Integer>();
        userNumberGamesMap.put("totalNumberGamesUser", totalNumberGamesUser);
        userNumberGamesMap.put("maxNumberGamesUser", maxNumberGamesUser);
        userNumberGamesMap.put("minNumberGamesUser", minNumberGamesUser);
        userNumberGamesMap.put("avgNumberGamesUser", avgNumberGamesUser);
        return userNumberGamesMap;
    }

    public Map<String, Integer> getPointsGlobal(){
        Integer totalPoints = getTotalPoints();
        Integer minPoints = getMinPoints();
        Integer maxPoints = getMaxPoints();
        Integer avgPoints = getAvgPoints();
        Map<String, Integer> pointsMap = new HashMap<String, Integer>();
        pointsMap.put("totalPoints", totalPoints);
        pointsMap.put("minPoints", minPoints);
        pointsMap.put("maxPoints", maxPoints);
        pointsMap.put("avgPoints", avgPoints);
        return pointsMap;
    }
}
