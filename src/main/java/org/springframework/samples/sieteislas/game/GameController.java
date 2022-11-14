package org.springframework.samples.sieteislas.game;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/games")
public class GameController {
    private static final String VIEWS_GAMES_LOBBY = "games/gameLobby";
    private static final String VIEWS_CREATE_GAME_FORM = "games/createNewGameView";

    private GameService gameService;
    private GameStatisticsService gameStatisticService;
    private PlayerService playerService;
    private UserService userService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, UserService userService,
                            GameStatisticsService gameStatisticService){
        this.gameService = gameService;
        this.playerService = playerService;
        this.userService = userService;
        this.gameStatisticService = gameStatisticService;
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
        if(game.getPlayers().size() < 2){
            this.gameService.delete(game);
        } else{
            //this.gameService.kickPlayer(game, principal.getName());
        }       
        return "redirect:/"; 
    }

    @GetMapping("/lobby/{id}/kick")
    public String kickFromLobby(@RequestAttribute("playerId") String playerId, Principal principal, ModelMap model){
        Game game = this.gameService.findById(Integer.valueOf(playerId));
        if(game.getPlayers().size()==1){
            this.gameService.delete(game);
            return "welcome";
        } else{
            this.gameService.kickPlayer(game, principal.getName());
            String redirect = String.format("redirect:/games/lobby/%s", playerId);
            return redirect;
        }        
    }

}
