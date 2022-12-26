package org.springframework.samples.sieteislas.statistics.playerStatistics;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.player.Player;
/* import org.springframework.samples.sieteislas.player.Player; */
import org.springframework.samples.sieteislas.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Audited
@Entity
@Table(name="playerStatistics")
public class PlayerStatistics extends BaseEntity{

	@NotNull
	@Column(name = "total_points")
    private Integer totalPoints;
	
	@NotNull
	@Column(name = "time_played")
    private Double timePlayed;
	
	@NotNull
	@Column(name = "games_played")
    private Integer gamesPlayed;
	
	@NotNull
	@Column(name = "games_won")
    private Integer gamesWon;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
	private Player player;

}
