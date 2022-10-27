package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.beans.Transient;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="game_statistics")
public class GameStatistics extends BaseEntity{

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @NotNull
    @Column(name="game_creator_name")
    private String gameCreatorName;

    @NotNull
    private Double duration; /* In seconds */

    @OneToMany(mappedBy="gameStatistics")
    private List<PlayerPointsMap> playerPoints;

/*###################################################################################################################################################### */
           //                                        TO DO
/*###################################################################################################################################################### */
    @Transient
    public Integer totalPoints(){
        return null;
    }

    @Transient
    public String winner(){ /* String hasta que implementemos la entidad Player */
        return null;
    }

    @Transient
    public Integer points(){
        return null;
    }

}
