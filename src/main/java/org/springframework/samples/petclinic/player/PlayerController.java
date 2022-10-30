package org.springframework.samples.petclinic.player;


import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {
	
	private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }
}
