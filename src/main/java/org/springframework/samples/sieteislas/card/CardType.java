package org.springframework.samples.sieteislas.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.sieteislas.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="card_types")
public class CardType extends BaseEntity{
    @NotNull
    @Column(name="name", unique=true)
    private String name;
    @NotNull
    @Column(name="image", unique=true)
    private String image;
}
