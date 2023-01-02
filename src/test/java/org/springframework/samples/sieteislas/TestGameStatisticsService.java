package org.springframework.samples.sieteislas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestGameStatisticsService {
    @Autowired
    private GameStatisticsService gss;

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
    void testGetNumberGamesGlobalMap() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("totalNumberGames", 2);
        expected.put("minNumberGames", 1);
        expected.put("maxNumberGames", 1);
        expected.put("avgNumberGames", 1);
        Map<String, Integer> actual = gss.getNumberGamesGlobalMap();
        assertEquals(expected, actual);
    }


}
