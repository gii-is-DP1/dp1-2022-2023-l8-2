package org.springframework.samples.sieteislas.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.card.CardRepository;
import org.springframework.samples.sieteislas.card.CardType;
import org.springframework.samples.sieteislas.card.CardTypeRepository;
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
    private final GameInvitationRepository gameInvitationRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final CardTypeRepository cardTypeRepository;
    private final CardRepository cardRepository;

    @Autowired
    public GameService(GameRepository gameRepository, GameInvitationRepository gameInvitationRepository, PlayerRepository playerRepository, 
        UserRepository userRepository, CardTypeRepository cardTypeRepository, CardRepository cardRepository){
        this.gameRepository = gameRepository;
        this.gameInvitationRepository = gameInvitationRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
        this.cardTypeRepository = cardTypeRepository;
        this.cardRepository = cardRepository;
    }

    public Game setUpNewGame(Game game, String creatorName) {
        game.setCreatorUsername(creatorName);
        game.setActive(true);
        game.setPlayerTurn(0);
        game.setStart(LocalDateTime.now());
        game.setDiceRoll(1);
        game.setHasRolledDice(false);
        game.setNumCardsToPay(0);

        GameStatistics statistics = GameStatistics.createDefault(game);
        game.setStatistics(statistics);

        this.gameRepository.save(game);

        User user = this.userRepository.findById(creatorName).get();
        Player creator = this.playerRepository.findPlayerByUser(user);
        creator.setGame(game);
        this.playerRepository.save(creator);

        return game;
    }

    public List<Card> createDeck(Game game) {
    	List<Card> cartas = new ArrayList<Card>();
        for (int i=0; i < 66; i++) {
        	Card card = new Card();
        	card.setGame(game);
        	if (i < 27) {//doblones
        		card.setCardType(getCardType("coin"));
        	} else if ( i >= 27 && i < 30) {//calices
        		card.setCardType(getCardType("coup"));
        	} else if ( i >= 30 && i < 33) {//rubies
        		card.setCardType(getCardType("ruby"));
        	} else if ( i >= 33 && i < 36) {//diamantes
        		card.setCardType(getCardType("diamond"));
        	} else if ( i >= 36 && i < 40) {//collares
        		card.setCardType(getCardType("necklace"));
        	} else if ( i >= 40 && i < 44) {//mapas
        		card.setCardType(getCardType("bottle"));
        	} else if ( i >= 44 && i < 48) {//coronas
        		card.setCardType(getCardType("crown"));
        	} else if ( i >= 48 && i < 54) {//revolveres
        		card.setCardType(getCardType("pistol"));
        	} else if ( i >= 54 && i < 60) {//espadas
        		card.setCardType(getCardType("sword"));
        	} else {//barriles
        		card.setCardType(getCardType("rum"));
        	}
            card.setGame(game);
            cartas.add(card);
        }

        Collections.shuffle(cartas);
        for(Card c : cartas){
        this.cardRepository.save(c);
        }

        return cartas;
    }

    private CardType getCardType(String name){
        return cardTypeRepository.getTypeByName(name);
    }

    public void moveCardToPlayer(Card card, Player player) {
        card.setGame(null);
        card.setPlayer(player);
        this.cardRepository.save(card);
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

    public String getCurrentPlayerName(Game game, String name) {
        String currentPlayerName = game.getPlayers().get(game.getPlayerTurn()).getUser().getUsername();
        return currentPlayerName;
    }

    public boolean isCurrentPlayer(String currentPlayerName, String principalName){
        return currentPlayerName.equals(principalName);
    }

    public void kickOfGame(Integer playerId) {
        Player p = this.playerRepository.findById(playerId).get();
        p.setGame(null);
        this.playerRepository.save(p);
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
    	
    	Player playing = game.getPlayers().get(game.getPlayerTurn());
    	Integer numCards = playing.getCards().size();
    	
    	return game.getDeck().subList(calculateLower(numCards, diceRoll), calculateHigher(numCards, diceRoll) + 1);
    }
    
    Predicate<Card> isCoin = c -> c.getCardType().getId().equals(1);
    
    Function<List<Card>,Integer> numCoins = l -> (int) l.stream()
    		.filter(isCoin)
    		.count();
    
    Function<List<Card>,Integer> points = l -> {
    	
    	Integer nC = numCoins.apply(l);
    	List<Integer> pointsPerNumOfSets = List.of(0,1,3,7,13,21,30,40,50,60);
    	
    	Integer numOfSets = l.stream()
    			.filter(isCoin.negate())
    			.map(c->c.getCardType().getName())
    			.collect(Collectors.toSet())
    			.size();
    	
    	return nC + pointsPerNumOfSets.get(numOfSets);
    };
    
    
    public Map<Player,Integer> scoreboard(Game g) {
    	
    	Map<Player,Integer> scoreboard = new HashMap<>();
    	
    	for(Player p:g.getPlayers()) {
    		
    		Integer pts = points.apply(p.getCards());
    		scoreboard.put(p, pts);
    	}
    	
    	return scoreboard;
    }
    
    private Player resolveDraw(List<Player> possibleWinners) {
    	
    	Player winner = null;
    	
    	Integer maxCoins = Collections.max(possibleWinners.stream()
				.map(p->numCoins.apply(p.getCards()))
				.toList());
		
		possibleWinners = possibleWinners.stream()
				.filter(p->numCoins.apply(p.getCards()).equals(maxCoins))
				.toList();
		
		if(possibleWinners.size() == 1)
			winner = possibleWinners.get(0);
		
		else {
			
			Integer random = (int) Math.round(Math.random() *
					(possibleWinners.size() - 1));
			winner = possibleWinners.get(random);
		}
		
		return winner;
    }
    
    public Player winner(Game g) {
    	
    	Player winner = null;
    	
    	Map<Player,Integer> scoreboard = scoreboard(g);
    	
    	Integer biggestMark = scoreboard.values().stream()
    			.max(Comparator.naturalOrder())
    			.get();
    	
    	List<Player> possibleWinners = scoreboard.entrySet().stream()
    			.filter(x->x.getValue().equals(biggestMark))
    			.map(Map.Entry::getKey)
    			.toList();
    	
    	if(possibleWinners.size() == 1)
    		winner = possibleWinners.get(0);
    	else
    		winner = resolveDraw(possibleWinners);
    	
    	return winner;
    }
    
    public void gameEnd(Game g) {
    	
    	Map<Player,Integer> scoreboard = scoreboard(g);
    	Player winner = winner(g);
    	
    	GameStatistics stats = new GameStatistics();
    	
    	/*stats.setWinner(winner);
    	stats.setPoints(scoreboard.get(winner));*/
    	
    	Integer totalPoints = scoreboard.values().stream()
    			.reduce(0, Integer::sum);
    	
    	//stats.setTotalPoints(totalPoints);
    	
    	//gameStatisticsRepository.save(stats);
    }

    @Transactional
    public void selectNewCreator(Game game) {
        Player p = game.getPlayers().get(0);
        this.gameRepository.updateCreator(game.getId(), p.getUser().getUsername());
    }

    @Transactional
    public void toggleActive(Game game, boolean b) {
        this.gameRepository.toggleActive(game.getId(), b);
    }
    
	public void toggleHasRolledDice(Game game, Boolean b) {
		game.setHasRolledDice(b);
    	gameRepository.save(game);
	}

    @Transactional
    public int setNumCardsToPay(Game game, Card card) {
        int chosenIsland = game.getDeck().indexOf(card)+1;//Islands are numbered 1 to 6, hence the +1.
        int rolledIsland = game.getDiceRoll()+1;
        int toPay = Math.abs(rolledIsland-chosenIsland);
        game.setNumCardsToPay(toPay);
    	gameRepository.save(game);
        
        return Math.abs(rolledIsland-chosenIsland);
    }

    @Transactional
    public int decrementNumCardsToPay(Game game) {
        int decrementedValue = game.getNumCardsToPay()-1;
        game.setNumCardsToPay(decrementedValue);
        gameRepository.save(game);
        return decrementedValue;
    }

    @Transactional
    public void passTurn(Game game) {
		toggleHasRolledDice(game, false);
        this.gameRepository.setNumCardsToPay(game.getId(), 0);
        calcNextPlayer(game);
    }

    @Transactional
    public void calcNextPlayer(Game game){
        int currentPlayer = game.getPlayerTurn();
        int numPlayers = game.getPlayers().size();
        int nextPlayer = (currentPlayer+1) % numPlayers;
        
        this.gameRepository.setPlayerTurn(game.getId(), nextPlayer);
    }

    public void invitePlayerToGame(String hostUsername, String invitedUsername, String gameId) {
        User host = this.userRepository.findById(hostUsername).get();
        User guest = this.userRepository.findById(invitedUsername).get();
        Game game = this.gameRepository.findById(Integer.valueOf(gameId)).get();
        
        GameInvitation invitation = new GameInvitation();
        invitation.setGame(game);
        invitation.setHost(host);
        invitation.setGuest(guest);

        this.gameInvitationRepository.save(invitation);
    }

    public void declineGameInvitation(String invitationId){
        GameInvitation invitation = this.gameInvitationRepository.findById(Integer.valueOf(invitationId)).get();
        this.gameInvitationRepository.delete(invitation);
    }

    public Integer acceptGameInvitation(String invitationId) throws FullGameException{
        GameInvitation invitation = this.gameInvitationRepository.findById(Integer.valueOf(invitationId)).get();
        Game game = invitation.getGame();
        if(game.getPlayers().size()>=4){
            throw new FullGameException();
        } else{
            Player guest = invitation.getGuest().getPlayer();
            guest.setGame(game);

            this.gameInvitationRepository.delete(invitation);

            return game.getId();
        }
        
    }

    public List<GameInvitation> getInvitationsOfUser(String name) {
        return this.gameInvitationRepository.findAllByUser(name);
    }


}
