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
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.HashMap;
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
    private GameStatisticsService gss;
    
    @Autowired
	private GameService gameService;
	
		

    @Test
    void testGetTimePlayedGlobalMap() {
        Map<String, Double> expected = new HashMap<>();
        expected.put("avgTimePlayed", 1200.00);
        expected.put("maxTimePlayed", 1400.00);
        expected.put("minTimePlayed", 1000.00);
        expected.put("totalTimePlayed", 2400.00);
        Map<String, Double> actual = gss.getTimePlayedGlobalMap();
        assertEquals(expected, actual);
    }

    @Test
	void testCreateDeck() {
		Game g1 = this.gameService.findById(1);
		List<Card> deck = this.gameService.createDeck(g1);
		Integer numCardTypes = deck.stream().map(c -> c.getCardType())
                                        .collect(Collectors.toSet())
                                        .size();
        assertEquals(numCardTypes, 1);
		assertEquals(deck.size(), 66);
		assertEquals(deck.get(33).getGame().getId(), 1);
		//assertEquals(deck.get(33).getCardType().getId(), 1);
		//assertThat(cardTypes.keySet().contains("coin")); 
	}
}
