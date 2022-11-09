package org.springframework.samples.petclinic.player;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.message.Message;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.statistics.achievement.Achievement;
import org.springframework.samples.petclinic.statistics.gameStatistics.PlayerPointsMap;
import org.springframework.samples.petclinic.statistics.playerStatistics.PlayerStatistics;
import org.springframework.samples.petclinic.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity{
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "username")
	private User user;
	
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	private List<Card>	cards;
	
	@OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
	private PlayerStatistics statistics;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	@OneToMany(mappedBy = "player")
	private List<PlayerPointsMap> playerPointsMap;

	@OneToMany(mappedBy = "player")
	private List<Message> messages;

	@ManyToMany
	@JoinTable(name="player_achievements", 
				joinColumns = @JoinColumn(name="player_id"),
				inverseJoinColumns = @JoinColumn(name="achievement_id"))
	private List<Achievement> achievements;


}
