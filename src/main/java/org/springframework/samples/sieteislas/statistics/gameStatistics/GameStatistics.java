package org.springframework.samples.sieteislas.statistics.gameStatistics;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.samples.sieteislas.game.Game;
import org.springframework.samples.sieteislas.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Audited
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

    @OneToMany(mappedBy="gameStatistics", cascade=CascadeType.ALL)
    @NotAudited
    private List<PlayerPointsMap> playerPoints;

    @NotNull
    @Column(name="month")
    private Integer month;

    @NotNull
    @Column(name="year")
    private Integer year;

    @Column(name="points")
    public Integer points;
    
    public static GameStatistics createDefault(Game game) {
        GameStatistics statistics = new GameStatistics();
        statistics.setGame(game);
        statistics.setGameCreatorName(game.getCreatorUsername());
        statistics.setDuration((double) game.getStart().until(game.getEnd(), ChronoUnit.MINUTES));
        statistics.setMonth(YearMonth.now().getMonthValue());
        statistics.setYear(YearMonth.now().getYear());
        return statistics;
    }

}
