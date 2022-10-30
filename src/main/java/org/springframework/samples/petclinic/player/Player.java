package org.springframework.samples.petclinic.player;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.statistics.achievement.Metric;
import org.springframework.samples.petclinic.statistics.playerStatistics.PlayerStatistics;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity{
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	private List<Card>	cards;
	
	@OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
	private PlayerStatistics statistics;


}
