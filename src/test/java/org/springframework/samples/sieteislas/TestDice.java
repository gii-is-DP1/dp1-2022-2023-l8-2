package org.springframework.samples.sieteislas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TestDice {
	
	@MockBean
	Game game;
	
	@MockBean
	GameService gameService;
	
	@Test
	public void testDice() throws Exception {
		
		List<Integer> diceRolls = new ArrayList<>();
		
		for(int i = 0; i < 99; i++) {
			
			gameService.rollDice(game);
			int diceRoll = game.getDiceRoll();
			
			diceRolls.add(diceRoll);
		}
		
		if(diceRolls.stream().anyMatch(x-> x < 0 || 5 < x))
			throw new Exception("Dice is returning illegal values.");
		
		Map<Integer, Long> mem = diceRolls.stream()
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		if(mem.entrySet().stream().anyMatch(x-> 50 < x.getValue()))
			throw new Exception("Dice is not random: " + mem);
	}
	
	
}
