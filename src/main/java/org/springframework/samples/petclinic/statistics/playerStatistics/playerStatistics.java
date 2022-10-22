package org.springframework.samples.petclinic.statistics.playerStatistics;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="playerStatistics")
public class playerStatistics extends BaseEntity{

    private Integer totalPoints;
    private Double timePlayed;
    private Integer gamesPlayed;
    private Integer gamesWon;
    private String favCard; //String temporal until Card is implemented.

}
