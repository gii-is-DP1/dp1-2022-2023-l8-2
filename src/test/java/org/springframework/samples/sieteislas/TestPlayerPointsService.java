package org.springframework.samples.sieteislas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.player.PlayerService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.samples.sieteislas.user.User;
import org.springframework.samples.sieteislas.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@Transactional
@Sql(scripts = "/test.sql" ,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestPlayerPointsService {
    @Autowired
    private PlayerPointsService pps;
    @Autowired
    private PlayerService ps;

    @Test
    void testGetTimePlayedUserMap() {
        Map<String, Double> expected = new HashMap<>();
        expected.put("avgTimePlayed", 1200.00);
        expected.put("maxTimePlayed", 1400.00);
        expected.put("minTimePlayed", 1000.00);
        expected.put("totalTimePlayed", 2400.00);
        Map<String, Double> actual = pps.getTimePlayedUserMap("testplayer1");
        assertEquals(expected, actual);
    }

    @Test
    void testGetNumberGamesUserMap() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("totalNumberGamesUser", 2);
        expected.put("maxNumberGamesUser", 1);
        expected.put("minNumberGamesUser", 1);
        expected.put("avgNumberGamesUser", 1);
        Map<String, Integer> actual = pps.getNumberGamesUserMap("testplayer1");
        assertEquals(expected, actual);
    }

    @Test
    void testGetPointsGlobal() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("totalPoints", 530);
        expected.put("minPoints", 50);
        expected.put("maxPoints", 150);
        expected.put("avgPoints", 106);
        Map<String, Integer> actual = pps.getPointsGlobal();
        assertEquals(expected, actual);
    }

    @Test
    void testGetPointsUser() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("totalPoints", 230);
        expected.put("minPoints", 90);
        expected.put("maxPoints", 140);
        expected.put("avgPoints", 115);
        Map<String, Integer> actual = pps.getPointsUser("testplayer1");
        assertEquals(expected, actual);
    }

    @Test
    void testGetPreviousGamesUser() {
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("gameId", List.of("2"));
        expected.put("gameCreator", List.of("testplayer2"));
        expected.put("gameDuration", List.of("1400.0"));
        expected.put("gamePoints", List.of("50"));
        Map<String, List<String>> actual = pps.getPreviousGamesUser("testplayer3");
        assertEquals(expected, actual);
    }

    @Test
    void testGetPlayerRankingPoints() {
        Map<String, List<String>> expected = new HashMap<>();
        expected.put("pointsUsernames", List.of("testplayer2", "testplayer1", "testplayer3"));
        expected.put("points", List.of("250", "230", "50"));
        Map<String, List<String>> actual = pps.getPlayerRankingPoints();
        assertEquals(expected, actual);
    }

    @Test
    void testGetWins() {
        Player player = ps.findByUsername("testplayer1");
        Integer actual = pps.getWins(player);
        assertEquals(actual, 1);
    }

    @Test
    void testGetPlayerRankingWins() {
        Game game1 = new Game();
        List<Map.Entry<Player, Integer>> actual = pps.getPlayerRankingWins();
        assertEquals(actual.get(0).getValue(), 1);
        assertEquals(actual.get(1).getValue(), 1);
        assertEquals(actual.get(2).getValue(), 0);
    }


}
