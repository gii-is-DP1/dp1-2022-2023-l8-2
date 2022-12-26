package org.springframework.samples.sieteislas.player;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Pageable paging = PageRequest.of(0, 5);

        Page<Player> playerPage = playerService.getAllPlayers(paging);
        List<Player> players = playerPage.getContent();
        model.put("playerPage", players);
        model.put("players", players);
        return PLAYERS_LISTING;
    }
}
