package org.springframework.samples.sieteislas.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
        game.setPlayerTurn(0);
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

        //Guardamos el nuevo juego en la bbdd
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

    public void exitGame(Game game, String name) {

        User user = this.userRepository.findById(name).get();
        Player p = this.playerRepository.findPlayerByUser(user);
        p.setGame(null);
        //game.setActive(false);
        this.playerRepository.save(p);  
    }

    public void delete(Game game) {
        Player p = game.getPlayers().get(0);
        p.setGame(null);
        this.playerRepository.save(p);

        this.gameRepository.delete(game);
    }

    public boolean isPlayer(List<Player> players, String principalName) {
        return players.stream()
                        .map(x->x.getUser().getUsername())
                        .anyMatch(x-> x.equals(principalName));
    }

    public void kickOfGame(Integer playerId) {
        Player p = this.playerRepository.findById(playerId).get();
        p.setGame(null);
        this.playerRepository.save(p);
    }

    public void nextPlayer(Game game){
        int currentPlayer = game.getPlayerTurn();
        int numPlayers = game.getPlayers().size();
        //TODO: calc next player: num mod numPlayers
        int nextPlayer = (currentPlayer+1) % numPlayers;
        
        game.setPlayerTurn(nextPlayer);
        this.gameRepository.save(game);
    }

    public void joinGame(Game game, String name) {
    	User user = this.userRepository.findById(name).get();
        Player p = this.playerRepository.findPlayerByUser(user);
        p.setGame(game);
        this.playerRepository.save(p);  
    }
    
    public void rollDice(Game game) {
    	
    	Double rand = Math.random() * 5;
    	Long num = Math.round(rand);
    	
    	game.setDiceRoll(num.intValue());
    }

}
