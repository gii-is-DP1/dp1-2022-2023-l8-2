package org.springframework.samples.sieteislas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.card.CardType;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.game.GameService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestGameService {
    
    @Autowired
	private GameService gameService;
	

    @Test
	void testCreateDeck() {
		Game g1 = this.gameService.findById(1);
		List<Card> deck = this.gameService.createDeck(g1);
		Map<String, Long> cardTypes = deck.stream()
				.map(c -> c.getCardType())
				.collect(Collectors.groupingBy(CardType::getName, Collectors.counting()));
		
		assertEquals(deck.size(), 66);
		assertEquals(deck.get(33).getGame().getId(), 1);
		assertThat(cardTypes.keySet().contains("coin")); 
		assertEquals(cardTypes.get("coin"), 27); 
		assertEquals(cardTypes.get("pistol"), 6);
	}
}
