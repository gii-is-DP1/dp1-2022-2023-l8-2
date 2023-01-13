package org.springframework.samples.sieteislas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.sieteislas.configuration.SecurityConfiguration;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsController;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatisticsService;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.FilterType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GameStatisticsController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration= SecurityConfiguration.class)
@WithMockUser(roles="ADMIN")
public class TestGameStatisticsController {
    @MockBean
    GameStatisticsService gameStatisticsService;
    @MockBean
    PlayerPointsService playerPointsService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllPreviousGamesGlobal() throws Exception {
        mockMvc.perform(get("/statistics/previousGames"))
            .andExpect(model().attributeExists("gameStatistics"))
            .andExpect(model().attributeExists("playerPointsMaps"))
            .andExpect(status().isOk())
            .andExpect(view().name("/gameStatistics/previousGamesListingGlobal"));
    }

    @Test
    public void testGetAllPreviousGamesUser() throws Exception {
        mockMvc.perform(get("/statistics/previousGames/user"))
            .andExpect(model().attributeExists("playerPointsMaps"))
            .andExpect(model().attributeExists("previousGamesUser"))
            .andExpect(status().isOk())
            .andExpect(view().name("/gameStatistics/previousGamesListingUser"));
    }

    @Test
    public void testGetDashboardData() throws Exception {
        mockMvc.perform(get("/statistics/dashboard"))
            .andExpect(model().attributeExists("globalTimePlayed"))
            .andExpect(model().attributeExists("globalNumberGames"))
            .andExpect(model().attributeExists("globalPoints"))
            .andExpect(status().isOk())
            .andExpect(view().name("/gameStatistics/statisticsDashboard"));
    }

    @Test
    public void testGetPlayerRanking() throws Exception {
        mockMvc.perform(get("/statistics/playerRanking"))
            .andExpect(model().attributeExists("playerRankingWins"))
            .andExpect(model().attributeExists("playerRankingPoints"))
            .andExpect(status().isOk())
            .andExpect(view().name("/gameStatistics/playerRankingListing"));
    }


}
