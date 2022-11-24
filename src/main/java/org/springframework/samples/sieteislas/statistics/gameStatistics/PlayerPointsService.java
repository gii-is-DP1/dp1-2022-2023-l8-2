package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;

@Service
public class PlayerPointsService {

    private final PlayerPointsRepository playerPointsRepository;

    @Autowired
    public PlayerPointsService(PlayerPointsRepository playerPointsRepository) {
        this.playerPointsRepository = playerPointsRepository;
    }
/*
    public Collection<PlayerPointsMap> getAllPointsMap(GameStatistics gameStatistics) {
        return playerPointsRepository.findByGameStatistics(gameStatistics.getGame().getId());
    }

 */

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

}
