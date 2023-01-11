package org.springframework.samples.sieteislas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameRepository;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestPossibleChoices {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Test
	public void testGetPossibleChoices() throws Exception {
		getPossibleChoices();
	}
	
	@Transactional
	private void getPossibleChoices() throws Exception {
		
		Game game = gameRepo.findById(1).get();
		
		Player player1 = playerRepo.findById(1).get();
		Player player2 = playerRepo.findById(2).get();
		
		List<Player> players = new ArrayList<>();
		players.add(player1); players.add(player2);
		
		game.setCreatorUsername(player1.getUser().getUsername());
		game.setPlayers(players);
		
		gameService.createDeck(game);
		
		game.setPlayerTurn(0);
		game.setDiceRoll(3);
		
		player1.setCards(List.of(new Card(), new Card()));
		
		assertEquals(5, gameService.possibleChoices(game).size());
	}
	
}
