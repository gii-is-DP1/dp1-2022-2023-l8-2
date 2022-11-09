package org.springframework.samples.petclinic.statistics.gameStatistics;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;
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

}