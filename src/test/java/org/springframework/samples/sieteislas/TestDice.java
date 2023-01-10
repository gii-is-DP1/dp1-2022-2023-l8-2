package org.springframework.samples.sieteislas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameRepository;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
public class TestDice {
	
	@Mock
	private Game game;
	
	@Mock
	private GameRepository gameRepo;
	
	protected GameService gameService;
	
	@BeforeEach
	private void setup() {
		gameService = new GameService(gameRepo,
				null, null, null, null, null, null);
	}
	
	@Test
	public void testDice() throws Exception {
		testDiceRolls();
	}
	
	@Transactional
	public void testDiceRolls() throws Exception {
		List<Integer> diceRolls = new ArrayList<>();
		
		for(int i = 0; i < 99; i++) {
			
			gameService.rollDice(game);
			diceRolls.add(game.getDiceRoll());
			
			//gameService.toggleHasRolledDice(game, false);
		}
		
		Map<Integer, Long> mem = diceRolls.stream()
			.collect(Collectors.groupingBy(Function.identity(),
					Collectors.counting()));
		
		//assertTrue(mem.entrySet().stream().anyMatch(x-> x.getValue() < 50));
		assertFalse(diceRolls.stream().anyMatch(x-> x < 0 || 5 < x));
		
		if(mem.entrySet().stream().anyMatch(x-> 50 < x.getValue()))
			throw new Exception(""+mem);
	}
	
	
}
