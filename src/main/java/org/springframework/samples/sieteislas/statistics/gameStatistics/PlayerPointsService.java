package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import static java.util.Collections.max;
import static java.util.Collections.min;

@Service
public class PlayerPointsService {

    private final PlayerPointsRepository playerPointsRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerPointsService(PlayerPointsRepository playerPointsRepository, PlayerRepository playerRepository) {
        this.playerPointsRepository = playerPointsRepository;
        this.playerRepository = playerRepository;
    }


    public Integer getWins(Player player) {
        List<PlayerPointsMap> allPlayerPointsMaps = this.playerPointsRepository.findAll();
        List<GameStatistics> gamesOfPlayer = allPlayerPointsMaps.stream()
                    .filter(x -> x.getPlayer().equals(player))
                    .map(x -> x.getGameStatistics())
                    .collect(Collectors.toList());
        List<PlayerPointsMap> playerPointsMapsOfPlayer = allPlayerPointsMaps.stream()
                    .filter(x -> gamesOfPlayer.contains(x.getGameStatistics()))
                    .collect(Collectors.toList());
        Map<GameStatistics, List<PlayerPointsMap>> gamesMap = playerPointsMapsOfPlayer.stream()
                    .collect(Collectors.groupingBy(PlayerPointsMap::getGameStatistics));
        List<Player> winners = new ArrayList<>();
        for (Map.Entry<GameStatistics, List<PlayerPointsMap>> entry: gamesMap.entrySet()){
            Player winner = entry.getValue().stream().max(Comparator.comparing(PlayerPointsMap::getPoints)).map(x -> x.getPlayer()).get();
            winners.add(winner);
        }
        Integer wins = (int) winners.stream().filter(x -> x.equals(player)).count();
        return wins;
    }

    public Collection<List<String>> getAllPlayerPointsMaps(Collection<GameStatistics> gameStatistics) {
        Collection<List<String>> allUsernames = new ArrayList<>();
        for (GameStatistics gm : gameStatistics) {
            List<String> usernames = new ArrayList<>();
            for (PlayerPointsMap ppm : gm.getPlayerPoints()) {
                usernames.add(ppm.getPlayer().getUser().getUsername());
            }
            allUsernames.add(usernames);
        }
        return allUsernames;
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
        List<String> gameDuration = playerPointsRepository.findGameDurationsUser(currentUser).stream().map(Object::toString).collect(Collectors.toList());
        List<String> gamePoints = playerPointsRepository.findGamePointsUser(currentUser).stream().map(Object::toString).collect(Collectors.toList());
        Map<String, List<String>> previousGamesUser = new HashMap<>();
        previousGamesUser.put("gameId", gameId);
        previousGamesUser.put("gameCreator", gameCreator);
        previousGamesUser.put("gameDuration", gameDuration);
        previousGamesUser.put("gamePoints", gamePoints);
        return previousGamesUser;
    }

    public Map<String, List<String>> getPlayerRankingPoints(){
        List<String> points = playerPointsRepository.findPointsRanked().stream().map(Object::toString).collect(Collectors.toList());
        List<String> pointsUsernames = playerPointsRepository.findUsersRankedByPoints().stream().map(Object::toString).collect(Collectors.toList());
        Map<String, List<String>> playerRanking = new HashMap<>();
        playerRanking.put("points", points);
        playerRanking.put("pointsUsernames", pointsUsernames);
        return playerRanking;
    }

    public List<Map.Entry<Player, Integer>> getPlayerRankingWins(){
        Iterable<Player> players = playerRepository.findAll();
        Map<Player, Integer> playerWinsMap = new HashMap<>();
        for (Player player : players){
            playerWinsMap.put(player, getWins(player));
        }
        List<Map.Entry<Player, Integer>> list = new ArrayList<>(playerWinsMap.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }

    public Map<String, List<String>> getPlayersPointsEndGame(Integer gameId) {
        List<String> points = playerPointsRepository.findPointsEndGameRanked(gameId).stream()
        		.map(Object::toString)
        		.collect(Collectors.toList());
        List<String> usernames = playerPointsRepository.findUsernameEndGameRankedByPoints(gameId);
        Map<String, List<String>> playerPointsEndGame = new HashMap<>();
        playerPointsEndGame.put("points", points);
        playerPointsEndGame.put("usernames", usernames);
        return playerPointsEndGame;
    }

    public void calculatePointsOfPlayersInGame(Game game) {
        for(Player p : game.getPlayers()){
            calculateNewMapping(p, game);
        }
        checkAndResolveDraw(game); 
    }

    private void checkAndResolveDraw(Game game) {
        List<PlayerPointsMap> pointsMap = this.playerPointsRepository.findMappingsEndGameRanked(game.getId());
        Set<Integer> points = pointsMap.stream()
                                        .map(x -> x.getPoints())
                                        .collect(Collectors.toSet());
        Boolean thereIsDraw = pointsMap.size() != points.size(); //Not equal lenghts mean that a point value has repeated itself.
        if(thereIsDraw) { 
           //We decide an arbitrary winner in case of a draw.
            PlayerPointsMap firstPossibleWinner = pointsMap.stream().max(Comparator.comparing(PlayerPointsMap::getPoints)).get();
            firstPossibleWinner.setPoints(firstPossibleWinner.getPoints()+1);
            this.playerPointsRepository.save(firstPossibleWinner);
        }
    }

    private void calculateNewMapping(Player p, Game game) {
            PlayerPointsMap newPointMap = new PlayerPointsMap();
            newPointMap.setPlayer(p);
            newPointMap.setPoints(calculatePoints(p));
            newPointMap.setGameStatistics(game.getStatistics());
            this.playerPointsRepository.save(newPointMap);
    }

    private Integer calculatePoints(Player p) {
        Integer pts = points.apply(p.getCards());
        return pts;
    }

    //Points calculation logic; by joscasvaz
    Predicate<Card> isCoin = c -> c.getCardType().getName().equals("coin");
    
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

    public List<PlayerPointsMap> findMappingsEndGameRanked(Integer id) {
        return this.playerPointsRepository.findMappingsEndGameRanked(id);
    }
}
