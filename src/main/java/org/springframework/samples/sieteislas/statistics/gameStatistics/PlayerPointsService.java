package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.max;
import static java.util.Collections.min;

@Service
public class PlayerPointsService {

    private final PlayerPointsRepository playerPointsRepository;

    @Autowired
    public PlayerPointsService(PlayerPointsRepository playerPointsRepository) {
        this.playerPointsRepository = playerPointsRepository;
    }

    public Collection<List<String>> getAllPlayerPointsMaps(Collection<GameStatistics> gameStatistics) {
        Collection<List<String>> players = new ArrayList<>();
        for (GameStatistics gm: gameStatistics) {
            List<String> playerNested = new ArrayList<>();
            for (Player pl: gm.getGame().getPlayers()){
                playerNested.add(pl.getUser().getUsername());
            }
            players.add(playerNested);
        }
        return players;
    }

    public Map<String, Double> getTimePlayedUserMap(String currentUser){
        Double avgTimePlayedUser = playerPointsRepository.findAvgTimePlayedByUser(currentUser);
        Double maxTimePlayedUser = playerPointsRepository.findMaxTimePlayedByUser(currentUser);
        Double minTimePlayedUser = playerPointsRepository.findMinTimePlayedByUser(currentUser);
        Double totalTimePlayedUser = playerPointsRepository.findTotalTimePlayedByUser(currentUser);
        Map<String, Double> timePlayedUserMap = new HashMap<String, Double>();
        timePlayedUserMap.put("avgTimePlayed", avgTimePlayedUser);
        timePlayedUserMap.put("maxTimePlayed", maxTimePlayedUser);
        timePlayedUserMap.put("minTimePlayed", minTimePlayedUser);
        timePlayedUserMap.put("totalTimePlayed", totalTimePlayedUser);
        return timePlayedUserMap;
    }

    public Map<String, Integer> getNumberGamesUserMap(String currentUser){
        Integer totalNumberGamesUser = playerPointsRepository.getTotalNumberGamesByUser(currentUser);
        Integer maxNumberGamesUser = max(playerPointsRepository.getGroupedNumberGamesByUser(currentUser));
        Integer minNumberGamesUser = min(playerPointsRepository.getGroupedNumberGamesByUser(currentUser));
        Integer avgNumberGamesUser = (int)playerPointsRepository.getGroupedNumberGamesByUser(currentUser).stream().mapToDouble(d -> d).average().orElse(0.0);
        Map<String, Integer> userNumberGamesMap = new HashMap<String, Integer>();
        userNumberGamesMap.put("totalNumberGamesUser", totalNumberGamesUser);
        userNumberGamesMap.put("maxNumberGamesUser", maxNumberGamesUser);
        userNumberGamesMap.put("minNumberGamesUser", minNumberGamesUser);
        userNumberGamesMap.put("avgNumberGamesUser", avgNumberGamesUser);
        return userNumberGamesMap;
    }

    public Map<String, Integer> getPointsGlobal(){
        Integer totalPoints = playerPointsRepository.findTotalPoints();
        Integer minPoints = playerPointsRepository.findMinPoints();
        Integer maxPoints = playerPointsRepository.findMaxPoints();
        Integer avgPoints = playerPointsRepository.findAvgPoints();
        Map<String, Integer> pointsMap = new HashMap<String, Integer>();
        pointsMap.put("totalPoints", totalPoints);
        pointsMap.put("minPoints", minPoints);
        pointsMap.put("maxPoints", maxPoints);
        pointsMap.put("avgPoints", avgPoints);
        return pointsMap;
    }

    public Map<String, Integer> getPointsUser(String currentUser){
        Integer totalPoints = playerPointsRepository.findTotalPointsUser(currentUser);
        Integer minPoints = playerPointsRepository.findMinPointsUser(currentUser);
        Integer maxPoints = playerPointsRepository.findMaxPointsUser(currentUser);
        Integer avgPoints = playerPointsRepository.findAvgPointsUser(currentUser);
        Map<String, Integer> pointsUserMap = new HashMap<String, Integer>();
        pointsUserMap.put("totalPoints", totalPoints);
        pointsUserMap.put("minPoints", minPoints);
        pointsUserMap.put("maxPoints", maxPoints);
        pointsUserMap.put("avgPoints", avgPoints);
        return pointsUserMap;
    }

    public Map<String, List<String>> getPreviousGamesUser(String currentUser){
        List<String> gameId = playerPointsRepository.findGameIdsUser(currentUser).stream().map(Object::toString).collect(Collectors.toList());
        List<String> gameCreator = playerPointsRepository.findGameCreatorsUser(currentUser);
        //List<String> gamePlayers = playerPointsRepository.findGamePlayersUser(currentUser);
        List<String> gameDuration = playerPointsRepository.findGameDurationsUser(currentUser).stream().map(Object::toString).collect(Collectors.toList());
        List<String> gamePoints = playerPointsRepository.findGamePointsUser(currentUser).stream().map(Object::toString).collect(Collectors.toList());
        Map<String, List<String>> previousGamesUser = new HashMap<>();
        previousGamesUser.put("gameId", gameId);
        previousGamesUser.put("gameCreator", gameCreator);
        //previousGamesUser.put("gamePlayers", gamePlayers);
        previousGamesUser.put("gameDuration", gameDuration);
        previousGamesUser.put("gamePoints", gamePoints);
        return previousGamesUser;
    }
}
