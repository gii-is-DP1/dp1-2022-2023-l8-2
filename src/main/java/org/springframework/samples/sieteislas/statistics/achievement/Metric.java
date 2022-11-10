package org.springframework.samples.sieteislas.statistics.achievement;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.sieteislas.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="metrics")
public class Metric extends NamedEntity{

}
