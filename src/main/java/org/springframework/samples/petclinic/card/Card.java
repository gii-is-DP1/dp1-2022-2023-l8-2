package org.springframework.samples.petclinic.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cards")
public class Card extends BaseEntity{
    
    @ManyToOne
	@JoinColumn(name = "card_type_id")
	private CardType cardType;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    /* private Player player; ESPERANDO A IMPLEMENTAR EL TIPO PLAYER*/
    
}
