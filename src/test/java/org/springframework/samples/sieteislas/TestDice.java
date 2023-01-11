package org.springframework.samples.sieteislas;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameRepository;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestDice {
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private GameService gameService;
	
	@Test
	public void testDice() throws Exception {
		testDiceRolls();
	}
	
	@Transactional
	public void testDiceRolls() throws Exception {
		
		Game game = gameRepo.findById(1).get();
		gameService.setUpNewGame(game, game.getCreatorUsername());
		
		List<Integer> diceRolls = new ArrayList<>();
		
		for(int i = 0; i < 99; i++) {
			
			gameService.rollDice(game);
			diceRolls.add(game.getDiceRoll());
		}
		
		Map<Integer, Long> mem = diceRolls.stream()
			.collect(Collectors.groupingBy(Function.identity(),
					Collectors.counting()));
		
		assertTrue(mem.entrySet().stream().anyMatch(x-> x.getValue() < 50));
		assertFalse(diceRolls.stream().anyMatch(x-> x < 0 || 5 < x));
	}
	
	
}
