package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface PlayerPointsRepository extends CrudRepository<PlayerPointsMap, Integer> {

    @Query("SELECT round(sum(points),2) FROM PlayerPointsMap")
    public Integer findTotalPoints();

    @Query("SELECT round(min(points),2) FROM PlayerPointsMap")
    public Integer findMinPoints();

    @Query("SELECT round(max(points),2) FROM PlayerPointsMap")
    public Integer findMaxPoints();

    @Query("SELECT round(avg(points),2) FROM PlayerPointsMap")
    public Integer findAvgPoints();

    @Query("SELECT round(sum(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findTotalPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(min(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findMinPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(max(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findMaxPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(avg(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findAvgPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(sum(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findTotalTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(min(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findMinTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(max(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findMaxTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(avg(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findAvgTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT count(p) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer getTotalNumberGamesByUser(@Param("currentUser") String currentUser);

    @Query("SELECT count(p) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser GROUP BY p.gameStatistics.month, p.gameStatistics.year")
    public Collection<Integer> getGroupedNumberGamesByUser(@Param("currentUser") String currentUser);

}
