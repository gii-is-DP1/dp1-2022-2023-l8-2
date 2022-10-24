package org.springframework.samples.petclinic.game;

import java.time.Duration;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.message.Message;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.statistics.gameStatistics.GameStatistics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="games")
public class Game extends BaseEntity{
    
    @Transient
    @Min(value=1, message = "roll must be at least 1")
    @Max(value=6, message = "roll must be 6 at maximum")
    private int diceRoll;
    
    @Transient
    @NotNull
    @Min(value=0)
    private int turnNum;

    @Transient
    @NotNull
    private Double duration;

    @Transient
    @NotNull
    private List<Card> islands;

    /* private Player creator; */

    @OneToOne(mappedBy = "game")
    private GameStatistics statistics;

    @OneToMany(mappedBy = "game")
    private List<Message> chat;

    @OneToMany(mappedBy = "game")
    private List<Card> deck;

}
