package org.springframework.samples.sieteislas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.card.CardService;
import org.springframework.samples.sieteislas.configuration.SecurityConfiguration;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameController;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.message.MessageService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GameController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration= SecurityConfiguration.class)
@WithMockUser(roles="ADMIN")
public class TestManageChosenIsland {
	
	@MockBean
	private GameService gameService;
	
	@MockBean
	private GameStatisticsService gameStatisticService;
	
	@MockBean
	private PlayerService playerService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private CardService cardService;
	
	@MockBean
	private MessageService messageService;
	
	@MockBean
	private PlayerPointsService playerPointsService;
    
	@Autowired
    MockMvc mvc;
	
	@BeforeEach
	public void configureMock() {
		
		User user1 = User.createDefault();
		User user2 = User.createDefault();
		
		user2.setUsername("defaultUsername2");
		
		Player player1 = Player.createDefault();
		Player player2 = Player.createDefault();
		
		player1.setUser(user1);
		player2.setUser(user2);
		
		user1.setPlayer(player1);
		user2.setPlayer(player2);
		
		Game game = Game.createDefault();
		
		player1.setGame(game);
		player2.setGame(game);
		
		List<Player> players = new ArrayList<>();
		players.addAll(List.of(player1,player2));
		
		game.setPlayers(players);
		
		Card card1 = Card.createDefault();
		Card card2 = Card.createDefault();
		
		List<Card> cards = new ArrayList<>();
		cards.addAll(List.of(card1,card2));
		
		player1.setCards(cards);
		game.setDeck(cards);
		
		Mockito.when(gameService.findById(0)).thenReturn(game);
	}
	
    @Test
    public void testChooseIsland() throws Exception {
        
    	Game game = gameService.findById(0);
    	Player player = game.getPlayers().get(0);
    	
    	game.setPlayerTurn(0);
    	Integer idCardSelected = 0;
    	
    	mvc.perform(get(String.format("/gameBoard/{gameId}/chooseCard/{cardId}",
				game.getId().toString(), idCardSelected.toString())));
    	
    }
	
}
