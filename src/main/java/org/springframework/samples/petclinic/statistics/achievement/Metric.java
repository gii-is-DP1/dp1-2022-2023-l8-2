package org.springframework.samples.petclinic.statistics.achievement;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.NamedEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="metrics")
public class Metric extends NamedEntity{

}
