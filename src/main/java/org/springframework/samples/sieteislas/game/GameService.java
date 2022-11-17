package org.springframework.samples.sieteislas.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.message.Message;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerRepository;
import org.springframework.samples.sieteislas.statistics.achievement.Achievement;
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

    public Collection<Game> getActiveGames() {
        return  gameRepository.getActiveGames(true);
    }

    public Game findById(Integer id) {
        return this.gameRepository.findById(id).get();
    }

    public void kickPlayer(Game game, String name) {
        List<Player> players = game.getPlayers().stream()
                                                .filter(x-> !x.getUser().getUsername().equals(name))
                                                .collect(Collectors.toList());
                                                game.setPlayers(players);
        save(game);
    }

    public void delete(Game game) {
        Player p = game.getPlayers().get(0);
        p.setGame(null);
        this.playerRepository.save(p);

        this.gameRepository.delete(game);
    }

    public Integer getNumberGames() {
        return gameRepository.getNumberGames();
    }

}
