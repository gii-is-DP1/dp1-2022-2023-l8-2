package org.springframework.samples.sieteislas.message;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="messages")
public class Message extends BaseEntity{

    @Size(min=1, max= 256)
    @Column(name="body", length=256)
    private String body;

    @NotNull
    @DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
    @Column(name="date")
    private LocalDateTime date;

    @NotNull
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @NotNull
    @ManyToOne
    @JoinColumn(name="player")
    private Player player; 
}
