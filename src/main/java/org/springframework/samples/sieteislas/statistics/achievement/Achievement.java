package org.springframework.samples.sieteislas.statistics.achievement;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.samples.sieteislas.model.NamedEntity;
import org.springframework.samples.sieteislas.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievements")
public class Achievement extends NamedEntity {
    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "image")
    @NotBlank
    private String badgeImage;

    @Column(name = "threshold")
    @NotNull
    private Double threshold;
    
	@ManyToOne
    @JoinColumn(name = "metric_id")
	private Metric metric;

    @ManyToMany(mappedBy="achievements")
    private List<Player> players;


}
