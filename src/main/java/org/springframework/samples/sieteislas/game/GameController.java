package org.springframework.samples.sieteislas.game;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.card.CardService;
import org.springframework.samples.sieteislas.message.Message;
import org.springframework.samples.sieteislas.message.MessageService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/games")
public class GameController {
    private static final String VIEWS_GAMES_LOBBY = "games/gameLobby";
    private static final String VIEWS_CREATE_GAME_FORM = "games/createNewGameView";
    private static final String VIEWS_GAMES_LIST = "games/gamesList";
    private static final String VIEWS_GAMES_GAMEBOARD = "games/gameBoard";
    private static final String VIEWS_GAMES_END = "games/gameEnd";
    private static final String VIEWS_GAMES_INVITE = "games/invitation";

    private GameService gameService;
    private GameStatisticsService gameStatisticService;
    private PlayerService playerService;
    private UserService userService;
    private CardService cardService;
    private MessageService messageService;
    private PlayerPointsService playerPointsService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, UserService userService,
                          GameStatisticsService gameStatisticService, CardService cardService,
                          MessageService messageService, PlayerPointsService playerPointsService){
        this.gameService = gameService;
        this.playerService = playerService;
        this.userService = userService;
        this.gameStatisticService = gameStatisticService;
        this.cardService = cardService;
        this.messageService = messageService;
        this.playerPointsService = playerPointsService;
    }

    //GET ALL ACTIVE GAMES
    @GetMapping("/active/{page}")
    public String getActiveGames(@PathVariable("page") Integer page, Map<String, Object> model, Principal principal) {
        Pageable paging = PageRequest.of(page, 5);
        Page<Game>  gamesPage = gameService.getActiveGames(paging);
        Player actualPlayer = this.playerService.findByUsername(principal.getName());

        List<GameInvitation> invitations = this.gameService.getInvitationsOfUser(principal.getName());

        model.put("page", page);
        model.put("hasPrevious", gamesPage.hasPrevious());
        model.put("hasNext", gamesPage.hasNext());
        model.put("invitations", invitations);
        model.put("actualPlayer", actualPlayer);
        model.put("games", gamesPage.getContent());
        return VIEWS_GAMES_LIST;
    }

    //CREATING A NEW GAME
    @GetMapping("/new")
    public String initCreateGameForm( ModelMap model, Principal principal){
        Game game = new Game();
        Player actualPlayer = this.playerService.findByUsername(principal.getName());
        model.put("actualPlayer", actualPlayer);
        model.put("game", game);
        return VIEWS_CREATE_GAME_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Principal principal, @ModelAttribute("game") Game game, ModelMap model) {
        game = this.gameService.setUpNewGame(game, principal.getName());

        String redirect = String.format("redirect:/games/lobby/%s", game.getId());
        return redirect;
    }

    @GetMapping("/lobby/{id}")
    public String getGameLobby(@PathVariable("id") String id, Principal principal, ModelMap model, HttpServletResponse response) {
        Game game = this.gameService.findById(Integer.valueOf(id));

        response.addHeader("Refresh", "2");
        //Si no esta activo significa que la partida ya ha empezado y redirigimos.
        if(!game.getActive()) {
            String redirect = String.format("redirect:/games/gameBoard/%s", id);
            return redirect;
        }

        model.put("principalName", principal.getName());
        model.put("game", game);
        return VIEWS_GAMES_LOBBY;
    }

    @GetMapping("/join/{id}")
    public String joinLobby(@PathVariable("id") String id, Principal principal, ModelMap model) {
    	Game game = this.gameService.findById(Integer.valueOf(id));
    	this.gameService.joinGame(game, principal.getName());

    	 String redirect = String.format("redirect:/games/lobby/%s", id);
         return redirect;
    }

    @GetMapping("/lobby/{id}/exit")
    public String exitFromLobby(@PathVariable("id") String id, Principal principal, ModelMap model) {
        Game game = this.gameService.findById(Integer.valueOf(id));
        Boolean isPlayer = this.gameService.isPlayer(game.getPlayers(), principal.getName());
        if(isPlayer){
            if(game.getPlayers().size() < 2){
                this.gameService.delete(game);
            } else{
                this.gameService.exitGame(game, principal.getName());
                if(game.getCreatorUsername().equals(principal.getName())){
                    this.gameService.selectNewCreator(game);
                }
            }
        }
        return "redirect:/";
    }

    @GetMapping("/lobby/{id}/kick/{playerId}")
    public String kickFromLobby(@PathVariable("id") String id, @PathVariable("playerId") String playerId) {
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

    @GetMapping("/lobby/invitation/{gameId}")
    public String invitePlayerListing(@PathVariable("gameId") String gameId, ModelMap model, Principal principal){
        User user = this.userService.findUser(principal.getName()).get();
        List<User> notPlaying = this.userService.notPlaying(user.getFriends()); //Solo devolvemos los usuarios que no estén jugando en otra partida ya.
        model.put("friends", notPlaying);
        model.put("gameId", gameId);
        return VIEWS_GAMES_INVITE;
    }

    @GetMapping("/lobby/invitation/{gameId}/invite/{invitedUsername}")
    public String invitePlayerToGame(@PathVariable("gameId") String gameId, @PathVariable("invitedUsername") String invitedUsername, Principal principal){
        this.gameService.invitePlayerToGame(principal.getName(), invitedUsername, gameId);

        String redirect = String.format("redirect:/games/lobby/invitation/{gameId}", gameId);
        return redirect;
    }

    @GetMapping("/lobby/invitation/accept/{invitationId}")
    public String acceptInvitation(@PathVariable("invitationId") String invitationId, RedirectAttributes redirAttrs){
        try{
            Integer gameId = this.gameService.acceptGameInvitation(invitationId);
            String redirect = String.format("redirect:/games/lobby/%s", gameId);
            return redirect;
        } catch (FullGameException ex){
            redirAttrs.addFlashAttribute("gameFullMessage", "You were unable to join! The game is now full!");
            return "redirect:/games/active";
        }
    }

    @GetMapping("/lobby/invitation/decline/{invitationId}")
    public String declineInvitation(@PathVariable("invitationId") String invitationId){
        this.gameService.declineGameInvitation(invitationId);

        return "redirect:/games/active";
    }

    @GetMapping("/start/{gameId}")
    public String startGame(@PathVariable("gameId") String id, ModelMap model) {
        Game game = this.gameService.findById(Integer.valueOf(id));
        if(game.getActive()){ //comprobamos si la partida ha comenzado--> active: no ha comenzado, !active: ha comenzado
            List<Card> doblones = gameService.createDeck(game).stream()
                                            .filter(x->(x.getCardType().getName()).equals("coin"))
                                            .collect(Collectors.toList());
            //Repartimos 3 doblones a cada jugador
            for(Player player: game.getPlayers()) {
                for(int i=0; i < 3; i++){
                    Card doblon = doblones.stream()
                                            .filter(x-> x.getPlayer() == null)
                                            .findFirst().get();
                    this.gameService.moveCardToPlayer(doblon, player);
                    this.cardService.save(doblon);
                }
            }
            this.gameService.toggleActive(game, false);
        }
        String redirect = String.format("redirect:/games/gameBoard/%s", id);
        return redirect;
    }

    @GetMapping("/gameBoard/{gameId}")
    public String renderBoard(@PathVariable("gameId") String id, Principal principal, Map<String, Object>  model, HttpServletResponse response) {
        Game game = this.gameService.findById(Integer.valueOf(id));
        boolean isPlayer = this.gameService.isPlayer(game.getPlayers(), principal.getName());

        String currentPlayerName = this.gameService.getCurrentPlayerName(game, principal.getName());
        boolean isCurrentPlayer = this.gameService.isCurrentPlayer(currentPlayerName, principal.getName());

        if(!isCurrentPlayer) response.addHeader("Refresh", "2");

        User u = this.userService.findUser(principal.getName()).get();
        Player principalPlayer = this.playerService.findByUser(u);

        model.put("isPlayer", isPlayer);
        model.put("principalPlayer", principalPlayer);
        model.put("isCurrentPlayer", isCurrentPlayer);
        model.put("currentPlayerName", currentPlayerName);
        model.put("principalName", principal.getName());
        model.put("game", game);

        model.put("message", new Message());
        return VIEWS_GAMES_GAMEBOARD;
    }

    
    
    @PostMapping(value = "/gameBoard/{gameId}")
    public String postInChat(@PathVariable("gameId") String id, Principal principal, 
    		@ModelAttribute("message") Message message, ModelMap model){
    	if(message.getBody().isEmpty() || message.getBody().equals(null)) {
    		model.addAttribute("message", new Message());
    	} else {
            Game game = this.gameService.findById(Integer.valueOf(id)); 
        
            message.setGame(game);
            message.setDate(LocalDateTime.now());
        
    	    Player p = this.playerService.findByUsername(principal.getName());
    	    message.setPlayer(p);	
    	    this.messageService.save(message);
    	
            game.getChat().add(message);
            model.addAttribute("message", new Message());
    	}
        String redirect = String.format("redirect:/games/gameBoard/%s", id);
        return redirect;
    }
    

    @GetMapping("/gameBoard/{gameId}/rollDice")
    public String diceManager(@PathVariable("gameId") String id, ModelMap model, Principal principal, HttpServletResponse response) {
    	Game game = gameService.findById(Integer.valueOf(id));
        this.gameService.toggleHasRolledDice(game, true);
    	this.gameService.rollDice(game);

    	List<Card> possibleChoices = gameService.possibleChoices(game);

    	model.put("possibleChoices", possibleChoices);

        return renderBoard(id, principal, model, response);
    }

    @GetMapping("/gameBoard/{gameId}/chooseCard/{cardId}")
    public String manageChosenIsland(@PathVariable("gameId") String id, @PathVariable("cardId") String cardId, ModelMap model, Principal principal) {
    	Game game = gameService.findById(Integer.valueOf(id));
        Player currentPlayer = game.getPlayers().get(game.getPlayerTurn());
        Card card = cardService.findById(Integer.valueOf(cardId));

        int cardsToPay = this.gameService.setNumCardsToPay(game, card);
        this.gameService.moveCardToPlayer(card, currentPlayer);

        if(cardsToPay <= 0){
        	
        	if(game.getDeck().size() < 6)
            	return endGame(id, model, principal);
        	else
        		this.gameService.passTurn(game);
        }

        String redirect = String.format("redirect:/games/gameBoard/%s", id);
        return redirect;
    }

    @GetMapping("/gameBoard/{gameId}/discard/{cardId}")
    public String discardCard(@PathVariable("gameId") String id, @PathVariable("cardId") String cardId, ModelMap model, Principal principal){
    	Game game = gameService.findById(Integer.valueOf(id));
        Card card = cardService.findById(Integer.valueOf(cardId));

        this.cardService.delete(card);

        int leftToPay = this.gameService.decrementNumCardsToPay(game);

        if(leftToPay <= 0){
            this.gameService.passTurn(game);
        }
        String redirect = String.format("redirect:/games/gameBoard/%s", id);
        return redirect;
    }
    
    @GetMapping("/gameBoard/{gameId}/end")
    public String endGame(@PathVariable("gameId") String id, ModelMap model, Principal principal) {
    	  Game game = gameService.findById(Integer.valueOf(id));
        model.put("playerPointsEndGame", playerPointsService.getPlayersPointsEndGame(game.getId()));
        return "redirect:/games/gameBoard/" + id + "/end";
    }

}
