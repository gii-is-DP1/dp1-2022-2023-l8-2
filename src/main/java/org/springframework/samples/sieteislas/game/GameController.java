package org.springframework.samples.sieteislas.game;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.card.CardService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    private static final String VIEWS_GAMES_LOBBY = "games/gameLobby";
    private static final String VIEWS_CREATE_GAME_FORM = "games/createNewGameView";
    private static final String VIEWS_GAMES_LIST = "games/gamesList";
    private static final String VIEWS_GAMES_GAMEBOARD = "games/gameBoard";

    private GameService gameService;
    private GameStatisticsService gameStatisticService;
    private PlayerService playerService;
    private UserService userService;
    private CardService cardService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, UserService userService,
                            GameStatisticsService gameStatisticService, CardService cardService){
        this.gameService = gameService;
        this.playerService = playerService;
        this.userService = userService;
        this.gameStatisticService = gameStatisticService;
        this.cardService = cardService;
    }

    //GET ALL ACTIVE GAMES
    @GetMapping("/active")
    public String getActiveGames(Map<String, Object> model, Principal principal) {
        Collection<Game> games = gameService.getActiveGames();
        model.put("games", games);
        return VIEWS_GAMES_LIST;
    }

    //CREATING A NEW GAME
    @GetMapping("/new")
    public String initCreateGameForm( ModelMap model){
        Game game = new Game();
        model.put("game", game);
        return VIEWS_CREATE_GAME_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Principal principal, @ModelAttribute("game") Game game, ModelMap model){
        game = this.gameService.setUpNewGame(game, principal.getName());

        String redirect = String.format("redirect:/games/lobby/%s", game.getId());
        return redirect;
    }

    @GetMapping("/lobby/{id}")
    public String getGameLobby(@PathVariable("id") String id, Principal principal, ModelMap model){
        Game game = this.gameService.findById(Integer.valueOf(id));
        model.put("principalName", principal.getName());
        model.put("game", game);
        return VIEWS_GAMES_LOBBY;
    }

    @GetMapping("/lobby/{id}/exit")
    public String exitFromLobby(@PathVariable("id") String id, Principal principal, ModelMap model){
        Game game = this.gameService.findById(Integer.valueOf(id));
        Boolean isPlayer = this.gameService.isPlayer(game.getPlayers(), principal.getName());
        if(isPlayer){
            if(game.getPlayers().size() < 2){
                this.gameService.delete(game);
            } else{
                this.gameService.exitGame(game, principal.getName());
            }  
        }
        return "redirect:/"; 
    }

    @GetMapping("/lobby/{id}/kick/{playerId}")
    public String kickFromLobby(@PathVariable("id") String id, @PathVariable("playerId") String playerId){
        Game game = this.gameService.findById(Integer.valueOf(id));
        if(game.getPlayers().size()<2){ //Esta comprobacion para tenerla controlada en teoria nunca podriamos kickearnos a nosotros mismos.
            this.gameService.delete(game);
            return "welcome";
        } else{
            this.gameService.kickOfGame(Integer.valueOf(playerId));
            String redirect = String.format("redirect:/games/lobby/%s", id);
            return redirect;
        }        
    }

    @GetMapping("/gameBoard/{gameId}")
    public String startGame(@PathVariable("gameId") String id, ModelMap model){
        //Repartir cartas a jugadores
    	
        Game game = this.gameService.findById(Integer.valueOf(id));
        List<Card> cartas = gameService.createDeck(game);
        int i = 1;
        for (Player player: game.getPlayers()) {
        	Card doblon1 = this.cardService.findById(i);
        	player.getCards().add(doblon1);
        	cartas.remove(i-1);
        	i +=1;
        	
        	Card doblon2 = this.cardService.findById(i);
        	player.getCards().add(doblon2);
        	cartas.remove(i-1);
        	i +=1;
        	
        	Card doblon3 = this.cardService.findById(i);
        	player.getCards().add(doblon3);
        	cartas.remove(i-1);
        	i+=1;
        	
        }
        model.put("game", game);
        return VIEWS_GAMES_GAMEBOARD;
    }

    @GetMapping("/gameBoard")
    public String getGameBoard(ModelMap model){
        return VIEWS_GAMES_GAMEBOARD;
    }
    

    @GetMapping("/join/{id}")
    public String joinLobby(@PathVariable("id") String id, Principal principal, ModelMap model) {
    	Game game = this.gameService.findById(Integer.valueOf(id));
    	this.gameService.joinGame(game, principal.getName());

    	 String redirect = String.format("redirect:/games/lobby/%s", id);
         return redirect;
    }

    @GetMapping("/gameBoard/{gameId}/rollDice")
    public String diceManager(@PathVariable("gameId") String id, ModelMap model,
    		Principal principal) {
    	
    	Game game = gameService.findById(Integer.valueOf(id));
    	
    	gameService.rollDice(game);
    	List<Card> possibleChoices = gameService.possibleChoices(game);
    	
    	model.put("game", game);
    	model.put("possibleChoices", possibleChoices);
    	model.put("username", principal.getName());
    	
        return VIEWS_GAMES_GAMEBOARD;

    }

}
