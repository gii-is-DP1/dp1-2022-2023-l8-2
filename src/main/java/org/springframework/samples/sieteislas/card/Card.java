package org.springframework.samples.sieteislas.card;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cards")
public class Card extends BaseEntity {
    
    @ManyToOne
	@JoinColumn(name = "card_type_id")
	private CardType cardType;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
    
    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;   
     
}
