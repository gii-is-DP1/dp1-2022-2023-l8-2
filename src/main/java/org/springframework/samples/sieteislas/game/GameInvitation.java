package org.springframework.samples.sieteislas.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GameInvitation extends BaseEntity{
    
    @ManyToOne
    @JoinColumn(name="host")
    private User host;

    @ManyToOne
    @JoinColumn(name="guest")
    private User guest;

    @ManyToOne
    private Game game;

}
