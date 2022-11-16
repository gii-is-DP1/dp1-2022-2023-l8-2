package org.springframework.samples.sieteislas.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.samples.sieteislas.model.BaseEntity;
import org.springframework.samples.sieteislas.model.NamedEntity;


@Entity
@Table(name="card_types")
public class CardType extends NamedEntity{
    @NotNull
    @Column(name="image", unique=true)
    private String image;
}
