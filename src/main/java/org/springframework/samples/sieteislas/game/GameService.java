package org.springframework.samples.sieteislas.game;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.message.Message;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerRepository;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatistics;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsRepository;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.samples.sieteislas.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final GameStatisticsRepository gameStatisticsRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    @Autowired
    public GameService(GameRepository gameRepository, GameStatisticsRepository gameStatisticsRepository, PlayerRepository playerRepository, UserRepository userRepository){
        this.gameRepository = gameRepository;
        this.gameStatisticsRepository = gameStatisticsRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    public Game setUpNewGame(Game game, String creatorName) {
        game.setCreatorUsername(creatorName);
        game.setActive(true);
        game.setTurnNum(0);
        game.setDuration(0.0);
        game.setDiceRoll(1);

        List<Card> islands = new ArrayList<>();
        game.setIslands(islands);

        List<Message> chat = new ArrayList<>();
        game.setChat(chat);

        GameStatistics statistics = GameStatistics.createDefault(game);
        game.setStatistics(statistics);
        
        List<Card> deck = createDeck();
        game.setDeck(deck);

        
        User user = this.userRepository.findById(creatorName).get();
        Player creator = this.playerRepository.findPlayerByUser(user);
        creator.setGame(game);
        List<Player> players = List.of(creator);
        game.setPlayers(players);

        //Guardamos juego en la bbdd
        this.gameRepository.save(game);

        return game;
    }

    private List<Card> createDeck() {
        return null;
    }

    public void save(Game game) {
        this.gameRepository.save(game);
    }

    public Game findById(Integer id) {
        return this.gameRepository.findById(id).get();
    }
}
