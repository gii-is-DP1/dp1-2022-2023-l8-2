package org.springframework.samples.petclinic.message;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;
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
    private LocalDateTime date;

    @NotNull
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    /* private Player emisor; ESPERANDO A IMPLEMENTAR EL TIPO PLAYER*/
}
