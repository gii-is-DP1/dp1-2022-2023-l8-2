package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PlayerPointsService {

    private final PlayerPointsRepository playerPointsRepository;

    @Autowired
    public PlayerPointsService(PlayerPointsRepository playerPointsRepository) {
        this.playerPointsRepository = playerPointsRepository;
    }

    public Collection<PlayerPointsMap> getAllPlayerPointsMap(GameStatistics gameStatistics) {
        return playerPointsRepository.findByGameStatistics(gameStatistics.getGame().getId());
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



}
