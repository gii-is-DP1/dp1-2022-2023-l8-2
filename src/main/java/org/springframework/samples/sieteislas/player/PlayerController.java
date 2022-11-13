package org.springframework.samples.sieteislas.player;


import java.util.Collection;
import java.util.Map;

import org.springframework.samples.sieteislas.statistics.achievement.Achievement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/players/")
public class PlayerController {
	
	private final PlayerService playerService;
	private String PLAYERS_LISTING = "/players/PlayersListing";


    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
  //GET ALL
    @GetMapping("/")
    public String getAllPlayers(Map<String, Object> model) {
        Collection<Player> players = playerService.getAllPlayers();
        model.put("players", players);
        return PLAYERS_LISTING;
    }
}
