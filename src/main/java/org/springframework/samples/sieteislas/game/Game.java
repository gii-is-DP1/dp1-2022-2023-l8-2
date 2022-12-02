package org.springframework.samples.sieteislas.game;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.sieteislas.card.Card;
import org.springframework.samples.sieteislas.message.Message;
import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.samples.sieteislas.statistics.gameStatistics.GameStatistics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="games")
public class Game extends BaseEntity{

    @NotBlank
    @Size(min=3, max=25)
    @Column(name="game_name", length = 25)
    private String gameName;

    @NotNull
    private Boolean active;

    @Min(value=-1, message = "roll must be at least -1")
    @Max(value=5, message = "roll must be 5 at maximum")
    private int diceRoll;

    @NotNull
    @Min(value=0)
    private int playerTurn;

    @NotNull
    private Double duration;
    
    private String creatorUsername;

    @OneToOne(mappedBy = "game", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private GameStatistics statistics;

    @OneToMany(mappedBy = "game", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Message> chat;

    @OneToMany(mappedBy = "game", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Card> deck;

    @Size(min=1, max=4)
    @OneToMany(mappedBy = "game", cascade=CascadeType.PERSIST)
    private List<Player> players;

}
