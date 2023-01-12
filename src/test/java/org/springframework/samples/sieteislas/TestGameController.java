package org.springframework.samples.sieteislas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;

import org.assertj.core.util.Lists;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.sieteislas.card.CardService;
import org.springframework.samples.sieteislas.configuration.SecurityConfiguration;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameController;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.message.MessageService;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GameController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration= SecurityConfiguration.class)
@WithMockUser(roles="PLAYER")
public class TestGameController {
	
	private static final int TEST_GAME_ID = 1;
	
	@MockBean
	private GameService gameService;
	@MockBean
    PlayerService playerService;
	@MockBean
    UserService userService;
	@MockBean
    GameStatisticsService gameStatisticsService;
	@MockBean
    CardService cardService;
	@MockBean
    MessageService messageService;
	@MockBean
    PlayerPointsService playerPointsService;
	@Autowired
	MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Game game = new Game();
		game.setId(4);
		game.setGameName("Test Game 4");
		game.setActive(false);
		game.setHasRolledDice(false);
		game.setPlayerTurn(0);
		game.setStart(LocalDateTime.now());
		given(this.gameService.findById(5)).willReturn(new Game());
	}
	
	@Test
	public void testPostInChat() throws Exception {
	    mockMvc.perform(post("/games/gameBoard/{gameId}", TEST_GAME_ID)
							.with(csrf())	
							.param("body", "hola"))
	        .andExpect(status().isOk());
	        //.andExpect(model().attributeExists("message"));
	        //.andExpect(status().is3xxRedirection())
	        //.andExpect(view().name("/games/gameBoard"));
		    //.andExpect(redirectedUrl("/games/gameBoard/{gameId}"))
	    }
	
	@Test
	public void testGetGameLobby() throws Exception {
	    mockMvc.perform(get("/games/lobby/{id}", 1))
	        .andExpect(status().isOk())
	        //.andExpect(model().attributeExists("principalName"));
	        //.andExpect(status().is3xxRedirection())
		    .andExpect(view().name("/games/gameLobby"));
	    }
	
}
