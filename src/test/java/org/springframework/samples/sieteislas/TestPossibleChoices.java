package org.springframework.samples.sieteislas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestPossibleChoices {

	@Mock
	private Player player;
	
	@Mock
	private Game game;
	
	/*@Autowired
	private GameRepository gameRepo;*/
	
	@Mock
	private GameService gameService;
	
	@Test
	public void testGetPossibleChoices() {
		getPossibleChoices();
	}
	
	private void getPossibleChoices() {
		
		List<Player> gamePlayers = game.getPlayers();
		gamePlayers.add(player);
		
		game.setPlayers(gamePlayers);
		game.setPlayerTurn(game.getPlayers().indexOf(player) - 1);
		
		game.setDiceRoll(3);
		player.setCards(List.of(new Card(), new Card()));
		
		assertEquals(5, gameService.possibleChoices(game).size());
	}
	
}
