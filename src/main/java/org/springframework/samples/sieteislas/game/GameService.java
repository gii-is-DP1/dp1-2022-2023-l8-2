package org.springframework.samples.sieteislas.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.card.CardType;
import org.springframework.samples.sieteislas.card.CardTypeRepository;
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
    private final CardTypeRepository cardTypeRepository;

    @Autowired
    public GameService(GameRepository gameRepository, GameStatisticsRepository gameStatisticsRepository, PlayerRepository playerRepository, UserRepository userRepository, CardTypeRepository cardTypeRepository){
        this.gameRepository = gameRepository;
        this.gameStatisticsRepository = gameStatisticsRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
        this.cardTypeRepository = cardTypeRepository;
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

        List<Card> deck = createDeck(game);
        game.setDeck(deck);

        User user = this.userRepository.findById(creatorName).get();
        Player creator = this.playerRepository.findPlayerByUser(user);
        creator.setGame(game);
        
        List<Player> players = List.of(creator);
        game.setPlayers(players);

        this.gameRepository.save(game);

        return game;
    }

    public List<Card> createDeck(Game game) {
    	List<Card> cartas = new ArrayList<Card>();
    	
        for (int i=0; i < 66; i++) {
        	Card card = new Card();
        	card.setGame(game);
        	if (i < 27) {//doblones
        		card.setCardType(cardTypeRepository.findById(1).get());
        	} else if ( i >= 27 && i < 30) {//calices
        		card.setCardType(cardTypeRepository.findById(2).get());
        	} else if ( i >= 30 && i < 33) {//rubies
        		card.setCardType(cardTypeRepository.findById(3).get());
        	} else if ( i >= 33 && i < 36) {//diamantes
        		card.setCardType(cardTypeRepository.findById(4).get());
        	} else if ( i >= 36 && i < 40) {//collares
        		card.setCardType(cardTypeRepository.findById(5).get());
        	} else if ( i >= 40 && i < 44) {//mapas
        		card.setCardType(cardTypeRepository.findById(6).get());
        	} else if ( i >= 44 && i < 48) {//coronas
        		card.setCardType(cardTypeRepository.findById(7).get());
        	} else if ( i >= 48 && i < 54) {//revolveres
        		card.setCardType(cardTypeRepository.findById(8).get());
        	} else if ( i >= 54 && i < 60) {//espadas
        		card.setCardType(cardTypeRepository.findById(9).get());
        	} else {//barriles
        		card.setCardType(cardTypeRepository.findById(10).get());
        	}
        	cartas.add(card);
        }
        return cartas;
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
    	gameRepository.save(game);
    }
    
    private int calculateHigher(Integer numCards, int diceRoll) {
    	
    	int res = numCards + diceRoll;
    	
    	return (5 < res) ? 5 : res;
    }
    
    private int calculateLower(Integer numCards, int diceRoll) {
    	
    	int res = diceRoll - numCards;
    	
    	return (res < 0) ? 0 : res;
    }
    
    public List<Card> possibleChoices(Game game){
    	
    	int diceRoll = game.getDiceRoll();
    	List<Card> islands = game.getIslands();
    	
    	Player playing = game.getPlayers().get(game.getPlayerTurn());
    	Integer numCards = playing.getCards().size();
    	
    	return islands.subList(calculateLower(numCards, diceRoll),
    			calculateHigher(numCards, diceRoll));
    }

}
