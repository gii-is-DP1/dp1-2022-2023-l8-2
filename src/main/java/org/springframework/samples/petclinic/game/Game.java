package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="games")
public class Game extends BaseEntity{
    
}
