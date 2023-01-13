package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="player_points_maps")
public class PlayerPointsMap extends BaseEntity{
    @NotNull
    @ManyToOne
    @JoinColumn(name="game_statistics_id")
    private GameStatistics gameStatistics;
    
    @NotNull
    private Integer points;

    @NotNull
    @ManyToOne
    @JoinColumn(name="player")
    private Player player;

    public static PlayerPointsMap createFromScoreboard(Game g, Player p, Map<Player,Integer> scoreboard) {
        
    	PlayerPointsMap ppm = new PlayerPointsMap();
        ppm.setGameStatistics(g.getStatistics());
        ppm.setPlayer(p);
        ppm.setPoints(scoreboard.get(p));
        
        return ppm;
    }
    
    public static List<PlayerPointsMap> getFromScoreboard(Game g, Map<Player,Integer> scoreboard){
    	
    	return g.getPlayers().stream()
		.map(p->PlayerPointsMap.createFromScoreboard(g, p, scoreboard))
		.toList();
    }
    
}
