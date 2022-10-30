package org.springframework.samples.petclinic.statistics.achievement;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.card.CardType;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="metrics")
public class Metric extends NamedEntity{

}
