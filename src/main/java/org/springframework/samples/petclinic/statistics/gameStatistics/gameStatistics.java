package org.springframework.samples.petclinic.statistics.gameStatistics;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="gameStatistics")
public class gameStatistics extends BaseEntity{
    
    private String gameCreator; //String temporal hasta que implementemos el tipo Player
    //private List<String> players; String temporal hasta que implementemos el tipo Player

}
