package org.springframework.samples.petclinic.game;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    
    private static final String VIEWS_GAMES_LOBBY = "games/gameLobby";
    private static final String VIEWS_CREATE_GAME_FORM = "games/createNewGameView";

    private GameService gameService;
    private PlayerService playerService;
    private UserService userService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, UserService userService){
        this.gameService = gameService;
        this.playerService = playerService;
        this.userService = userService;
    }

    //CREATING A NEW GAME
    @GetMapping("/new")
    public String initCreateGameForm(Principal principal, ModelMap model){
        String creatorUsername = principal.getName();
        Game game = this.gameService.setUpNewGame(creatorUsername);
        model.put("game", game);
        return VIEWS_CREATE_GAME_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Principal principal, @Valid Game game, BindingResult result, ModelMap model){
        if (result.hasErrors()){
            model.put("errors", result.getAllErrors());
            model.put("game", game);
            return VIEWS_CREATE_GAME_FORM;
        } else{
            return "welcome";
        }
    }
}
