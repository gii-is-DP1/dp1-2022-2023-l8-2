package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardType;
import org.springframework.samples.petclinic.message.Message;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerRepository;
import org.springframework.samples.petclinic.statistics.gameStatistics.GameStatistics;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, UserRepository userRepository){
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    public Game setUpNewGame(String creatorName) {
        Game game = new Game();
        game.setGameName("New game");
        game.setCreatorUsername(creatorName);
        game.setActive(true);
        game.setTurnNum(0);
        game.setDuration(0.0);
        game.setDiceRoll(1);

        List<Card> islands = new ArrayList<>();
        game.setIslands(islands);

        List<Message> chat = new ArrayList<>();
        game.setChat(chat);

/*         GameStatistics statistics = new GameStatistics();
        game.setStatistics(statistics); */
        
        List<Card> deck = createDeck();
        game.setDeck(deck);

        List<Player> players = new ArrayList<>();
        User user = this.userRepository.findById(creatorName).get();
        Player creator = this.playerRepository.findPlayerByUser(user);
        players.add(creator);
        game.setPlayers(players);

        //Guardamos juego en la bbdd
        this.gameRepository.save(game);

        return game;
    }

    private List<Card> createDeck() {
        return null;
    }
    
}
