package org.springframework.samples.sieteislas.player;

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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.message.Message;
import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.statistics.achievement.Achievement;
import org.springframework.samples.sieteislas.statistics.gameStatistics.PlayerPointsMap;
import org.springframework.samples.sieteislas.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Audited
@Table(name = "players")
public class Player extends BaseEntity{
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "username")
	private User user;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	@NotAudited
	private List<Card>	cards;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	@OneToMany(mappedBy = "player")
	@NotAudited
	private List<PlayerPointsMap> playerPointsMap;

	@OneToMany(mappedBy = "player")
	@NotAudited
	private List<Message> messages;

	@ManyToMany
	@JoinTable(name="player_achievements",
				joinColumns = @JoinColumn(name="player_id"),
				inverseJoinColumns = @JoinColumn(name="achievement_id"))
	private List<Achievement> achievements;

    public String toString(){
        return user.getUsername();
    }

}
