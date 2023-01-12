package org.springframework.samples.sieteislas.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
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
@Audited
@Entity
@Table(name="games")
public class Game extends BaseEntity{

    @NotBlank
    @Size(min=3, max=25)
    @Column(name="game_name", length = 25)
    private String gameName;

    @NotNull
    private Boolean active;

    @NotNull
    @NotAudited
    private Boolean hasRolledDice;

    @Min(value=0, message = "roll must be at least 0")
    @Max(value=5, message = "roll must be 5 at maximum")
    private int diceRoll;

    @NotAudited
    private int numCardsToPay;

    @NotNull
    @Min(value=0)
    private int playerTurn;

    @NotNull
    @DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
    @Column(name="start")
    private LocalDateTime start;

    @DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
    @Column(name="end")
    private LocalDateTime end;
    
    private String creatorUsername;

    @OneToOne(mappedBy = "game", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @NotAudited
    private GameStatistics statistics;

    @OneToMany(mappedBy = "game", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @NotAudited
    private List<Message> chat;

    @OneToMany(mappedBy = "game", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @NotAudited
    private List<Card> deck;

    @Size(min=1, max=4)
    @OneToMany(mappedBy = "game", cascade=CascadeType.PERSIST)
    private List<Player> players;
    
    public static Game createDefault() {
    	
    	Game game = new Game();
    	
    	game.setActive(true);
    	game.setChat(new ArrayList<>());
    	game.setCreatorUsername("defaultPlayer");
    	game.setDeck(new ArrayList<>());
    	game.setDiceRoll(0);
    	game.setGameName("defaultGame");
    	game.setHasRolledDice(false);
    	game.setId(0);
    	game.setNumCardsToPay(0);
    	game.setPlayers(new ArrayList<>());
    	game.setPlayerTurn(0);
    	game.setStart(LocalDateTime.now());
    	game.setStatistics(new GameStatistics());
    	
    	return game;
    }

}
